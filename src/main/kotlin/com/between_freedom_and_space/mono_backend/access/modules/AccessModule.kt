package com.between_freedom_and_space.mono_backend.access.modules

import com.between_freedom_and_space.mono_backend.access.api.mappers.BaseUserRoleModelToModelMapper
import com.between_freedom_and_space.mono_backend.access.api.models.UserRoleModel
import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher
import com.between_freedom_and_space.mono_backend.access.components.impl.PathPatternMatcherImpl
import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserRole
import com.between_freedom_and_space.mono_backend.access.modules.qualifiers.AccessMappersQualifiers
import com.between_freedom_and_space.mono_backend.access.repository.*
import com.between_freedom_and_space.mono_backend.access.repository.impl.*
import com.between_freedom_and_space.mono_backend.access.service.InformationAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.InformationUserRolesService
import com.between_freedom_and_space.mono_backend.access.service.InteractionAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.InteractionUserRolesService
import com.between_freedom_and_space.mono_backend.access.service.impl.InformationAccessRulesServiceImpl
import com.between_freedom_and_space.mono_backend.access.service.impl.InformationUserRolesServiceImpl
import com.between_freedom_and_space.mono_backend.access.service.impl.InteractionAccessRulesServiceImpl
import com.between_freedom_and_space.mono_backend.access.service.impl.InteractionUserRolesServiceImpl
import com.between_freedom_and_space.mono_backend.access.service.mappers.UserRoleEntityToBaseUserRoleMapper
import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<BaseUserRoleModel, UserRoleModel>>(
        named(AccessMappersQualifiers.BASE_USER_ROLE_TO_USER_ROLE_MODEL_MAPPER)
    ) { BaseUserRoleModelToModelMapper() }
    single<ModelMapper<UserRole, BaseUserRoleModel>>(
        named(AccessMappersQualifiers.USER_ROLE_ENTITY_TO_BASE_MODEL_MAPPER)
    ) { UserRoleEntityToBaseUserRoleMapper() }
}

private val repositoryModule = module {
    single { CommonAccessedRolesRepositoryImpl() } bind CommonAccessedRolesRepository::class
    single { CommonAccessedUsersRepositoryImpl() } bind CommonAccessedUsersRepository::class
    single { CommonAccessRuleRepositoryImpl() } bind CommonAccessRuleRepository::class
    single { CommonUserRoleRepositoryImpl() } bind CommonUserRoleRepository::class
    single { CommonUserToRoleRepositoryImpl() } bind CommonUserToRoleRepository::class
}

private val componentsModule = module {
    single { PathPatternMatcherImpl() } bind PathPatternMatcher::class
}

private val pluginModule = module {

}

private val serviceModule = module {
    single { InformationAccessRulesServiceImpl() } bind InformationAccessRulesService::class
    single { InformationUserRolesServiceImpl(
        get(), get(),
        get(named(AccessMappersQualifiers.USER_ROLE_ENTITY_TO_BASE_MODEL_MAPPER)))
    } bind InformationUserRolesService::class
    single { InteractionAccessRulesServiceImpl() } bind InteractionAccessRulesService::class
    single { InteractionUserRolesServiceImpl() } bind InteractionUserRolesService::class
}

val accessModule = module {
    includes(mappersModule)
    includes(repositoryModule)
    includes(componentsModule)
    includes(pluginModule)
    includes(serviceModule)
}