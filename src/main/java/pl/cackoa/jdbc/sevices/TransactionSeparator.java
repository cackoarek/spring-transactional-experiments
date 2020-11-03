package pl.cackoa.jdbc.sevices;

import lombok.SneakyThrows;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class TransactionSeparator {



    public static <T> Optional<T> executeWithResult(Supplier<T> supplier) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> future = executor.submit(supplier::get);
        try {
            return Optional.ofNullable(future.get());
        } catch (Exception e) {
            System.out.println("Exception in a thread: " + e);
            return Optional.empty();
        } finally {
            executor.shutdown();
        }
    }

// simple version
//    @SneakyThrows
//    public static <T> Optional<T> executeWithResult2(Supplier<T> supplier) {
//        final Result<T> result = new Result<>();
//        Thread thread = new Thread(() -> result.entity = supplier.get());
//        thread.start();
//        thread.join();
//        return Optional.ofNullable(result.entity);
//    }



    public static boolean executeWithNoResult(Runnable runnable) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(runnable);
        try {
            future.get();
            return true;
        } catch (Exception e) {
            System.out.println("Exception in a thread: " + e);
            return false;
        } finally {
            executor.shutdown();
        }

    }

// simple version
//    @SneakyThrows
//    public static boolean executeWithNoResult2(Runnable runnable) {
//        Thread thread = new Thread(runnable);
//        thread.start();
//        thread.join();
//        return true;
//    }

    static class Result<T> {
        T entity = null;
    }




}
