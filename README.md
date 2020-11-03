# Spring @Transactional experiments

Some examples with `@Transactional` annotation in Spring including exception throwing scenarios.
Database used in this project is `HSQL DB` (in RAM), no need to install own DB.

Experiments with executing `@Transactional` methods in separate Threads - transaction manager should not handle exceptions to other thread.

1. Basic usage examples
1. Transaction rollback if exception occurs
1. Experiments with **Transaction separation** using other Threads

For details - see `ServiceTest`.