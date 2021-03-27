package com.spring.templates.bencmarks;

import com.spring.templates.repositories.CategoryRepository;
import lombok.SneakyThrows;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@RunWith(SpringRunner.class)
public class DataSourceBenchmark extends AbstractSpringComponentBenchmark {
    private static CategoryRepository repository;

    @Autowired
    public void setDataSource(CategoryRepository repository) {
        DataSourceBenchmark.repository = repository;
    }

    /*
     * There is no @Test annotated method within here, because the AbstractBenchmark
     * defines one, which spawns the JMH runner. This class only contains JMH/Benchmark
     * related code.
     */

   /* @Setup(Level.Trial)
    public void setupBenchmark() {
    }*/

    @SneakyThrows
    @Benchmark
    public void someBenchmarkMethod(Blackhole blackhole) {
        blackhole.consume(repository.findByDescription(""));
    }
}
