package com.ht117.movierank

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * JUnit Test Rule which overrides RxJava and Android schedulers for use in unit tests.
 *
 *
 * All schedulers are replaced with Schedulers.trampoline().
 */
class RxSchedulerRule : TestRule {

    private val trampoline = Schedulers.trampoline()

    private val schedulerFunction = { scheduler -> trampoline }

    private val schedulerFunctionLazy = { schedulerCallable -> trampoline }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            //@Throws(Throwable::class)
            override fun evaluate() {
                RxAndroidPlugins.reset()
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerFunctionLazy)

                RxJavaPlugins.reset()
                RxJavaPlugins.setIoSchedulerHandler(schedulerFunction)
                RxJavaPlugins.setNewThreadSchedulerHandler(schedulerFunction)
                RxJavaPlugins.setComputationSchedulerHandler(schedulerFunction)

                base.evaluate()

                RxAndroidPlugins.reset()
                RxJavaPlugins.reset()
            }
        }
    }
}