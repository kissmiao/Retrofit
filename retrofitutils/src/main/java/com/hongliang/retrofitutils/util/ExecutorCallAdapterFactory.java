package com.hongliang.retrofitutils.util;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;

import com.hongliang.retrofitutils.OkCall;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.support.v4.util.Preconditions.checkNotNull;


public final class ExecutorCallAdapterFactory extends CallAdapter.Factory {

    public static final CallAdapter.Factory INSTANCE = new ExecutorCallAdapterFactory();

    private ExecutorCallAdapterFactory() {
    }

    /**
     * Extract the raw class type from {@code type}. For example, the type representing
     * {@code List<? extends Runnable>} returns {@code List.class}.
     */
    public static Class<?> getRawType(Type type) {
        return CallAdapter.Factory.getRawType(type);
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != OkCall.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException(
                    "Call return type must be parameterized as Call2<Foo> or Call2<? extends Foo>");
        }
        final Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);

        final Executor callbackExecutor = retrofit.callbackExecutor();
        if (callbackExecutor == null) throw new AssertionError();

        return new CallAdapter<Object, Call<?>>() {
            @Override
            public Type responseType() {
                return responseType;
            }

            @Override
            public Call<Object> adapt(Call<Object> call) {
                return new ExecutorCallbackCall2<>(callbackExecutor, call);
            }
        };
    }

    static final class ExecutorCallbackCall2<T> implements OkCall<T> {
        private final Executor callbackExecutor;
        private final Call<T> delegate;

        /**
         * The executor used for {@link Callback} methods on a {@link Call}. This may be {@code null},
         * in which case callbacks should be made synchronously on the background thread.
         */
        ExecutorCallbackCall2(Executor callbackExecutor, Call<T> delegate) {
            this.callbackExecutor = callbackExecutor;
            this.delegate = delegate;
        }

        @SuppressLint("RestrictedApi")
        @Override
        public void enqueue(final Callback<T> callback) {
            checkNotNull(callback, "callback == null");
            delegate.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, final Response<T> response) {
                    callbackExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            if (delegate.isCanceled()) {
                                callResult(1, callback, ExecutorCallbackCall2.this, null, new IOException("Canceled"));
                            } else {
                                callResult(2, callback, ExecutorCallbackCall2.this, response, null);
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Call<T> call, final Throwable t) {
                    callbackExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            callResult(3, callback, ExecutorCallbackCall2.this, null, t);
                        }
                    });
                }
            });
        }


        private void callResult(int type, Callback<T> callback, OkCall<T> call, @Nullable Response<T> response, @Nullable Throwable t) {
            try {
                if (type == 1) {
                    callback.onFailure(call, new IOException("Canceled"));
                } else if (type == 2) {
                    callback.onResponse(call, response);
                } else if (type == 3) {
                    callback.onFailure(call, t);
                }
            } finally {
                CallManager.getInstance().remove(this);
            }
        }


        @Override
        public OkCall<T> tag(Object obj) {
            CallManager.getInstance().add(this, obj != null ? obj : "NO_TAG");
            return this;
        }

        @Override
        public boolean isExecuted() {
            return delegate.isExecuted();
        }

        @Override
        public Response<T> execute() throws IOException {
            return delegate.execute();
        }

        @Override
        public void cancel() {
            delegate.cancel();
        }

        @Override
        public boolean isCanceled() {
            return delegate.isCanceled();
        }

        @Override
        public OkCall<T> clone() {
            return new ExecutorCallbackCall2<>(callbackExecutor, delegate.clone());
        }


        @Override
        public Request request() {
            return delegate.request();
        }
    }
}
