package com.davcres.template.core.common.di

import org.koin.core.module.Module

interface KoinModuleLoader {
    val modules: List<Module>
}
