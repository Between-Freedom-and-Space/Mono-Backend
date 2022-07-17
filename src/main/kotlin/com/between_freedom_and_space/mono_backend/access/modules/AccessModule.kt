package com.between_freedom_and_space.mono_backend.access.modules

import com.between_freedom_and_space.mono_backend.access.api.mappers.*
import com.between_freedom_and_space.mono_backend.access.api.models.*
import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher
import com.between_freedom_and_space.mono_backend.access.components.impl.PathPatternMatcherImpl
import com.between_freedom_and_space.mono_backend.access.components.plugin.PluginAccessHandler
import com.between_freedom_and_space.mono_backend.access.components.plugin.impl.PluginAccessHandlerImpl
import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserRole
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.modules.qualifiers.AccessMappersQualifiers
import com.between_freedom_and_space.mono_backend.access.repository.*
import com.between_freedom_and_space.mono_backend.access.repository.impl.*
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRoleRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateUserRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.service.InformationAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.InformationUserRolesService
import com.between_freedom_and_space.mono_backend.access.service.InteractionAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.impl.InformationAccessRulesServiceImpl
import com.between_freedom_and_space.mono_backend.access.service.impl.InformationUserRolesServiceImpl
import com.between_freedom_and_space.mono_backend.access.service.impl.InteractionAccessRulesServiceImpl
import com.between_freedom_and_space.mono_backend.access.service.mappers.*
import com.between_freedom_and_space.mono_backend.access.service.models.*
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<BaseUserRoleModel, UserRoleModel>>(
        named(AccessMappersQualifiers.BASE_USER_ROLE_TO_USER_ROLE_MODEL_MAPPER)
    ) { BaseUserRoleModelToModelMapper() }
    single<ModelMapper<UserRole, BaseUserRoleModel>>(
        named(AccessMappersQualifiers.USER_ROLE_ENTITY_TO_BASE_MODEL_MAPPER)
    ) { UserRoleEntityToBaseUserRoleMapper() }
    single<ModelMapper<AccessRule, BaseAccessRuleModel>>(
        named(AccessMappersQualifiers.ACCESS_RULE_TO_BASE_MODEL_MAPPER)
    ) { AccessRuleEntityToBaseModelMapper() }
    single<ModelMapper<RuleCheckResult, HasAccessResponse>>(
       named(AccessMappersQualifiers.CHECK_ACCESS_RESULT_TO_RESPONSE_MAPPER)
    ) { CheckAccessResultToResponseMapper() }
    single<ModelMapper<CreateRoleRuleRequest, CreateRoleRuleModel>>(
        named(AccessMappersQualifiers.CREATE_ROLE_RULE_REQUEST_TO_MODEL_MAPPER)
    ) { CreateRoleRuleRequestToModelMapper() }
    single<ModelMapper<CreateRuleRequest, CreateRuleModel>>(
        named(AccessMappersQualifiers.CREATE_RULE_REQUEST_TO_MODEL_MAPPER)
    ) { CreateRuleRequestToModelMapper() }
    single<ModelMapper<CreateUserRuleRequest, CreateUserRuleModel>>(
        named(AccessMappersQualifiers.CREATE_USER_RULE_REQUEST_TO_MODEL_MAPPER)
    ) { CreateUserRuleRequestToModelMapper() }
    single<ModelMapper<UpdateRoleRuleRequest, UpdateRoleRuleModel>>(
        named(AccessMappersQualifiers.UPDATE_ROLE_RULE_REQUEST_TO_MODEL_MAPPER)
    ) { UpdateRoleRuleRequestToModelMapper() }
    single<ModelMapper<UpdateRuleRequest, UpdateRuleModel>>(
        named(AccessMappersQualifiers.UPDATE_RULE_REQUEST_TO_MODEL_MAPPER)
    ) { UpdateRuleRequestToModelMapper() }
    single<ModelMapper<UpdateUserRuleRequest, UpdateUserRuleModel>>(
        named(AccessMappersQualifiers.UPDATE_USER_RULE_REQUEST_TO_MODEL_MAPPER)
    ) { UpdateUserRuleRequestToModelMapper() }
    single<ModelMapper<CreateRuleModel, CreateRuleEntityModel>>(
        named(AccessMappersQualifiers.CREATE_RULE_MODEL_TO_ENTITY_MAPPER)
    ) { CreateRuleModelToEntityMapper() }
    single<ModelMapper<CreateRoleRuleModel, CreateRoleRuleEntityModel>>(
        named(AccessMappersQualifiers.CREATE_ROLE_RULE_MODEL_TO_ENTITY_MAPPER)
    ) { CreateRoleRuleModelToEntityMapper() }
    single<ModelMapper<CreateUserRuleModel, CreateUserRuleEntityModel>>(
        named(AccessMappersQualifiers.CREATE_USER_RULE_MODEL_TO_ENTITY_MAPPER)
    ) { CreateUserRuleModelToEntityMapper() }
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
    single { PluginAccessHandlerImpl(get(), get()) } bind PluginAccessHandler::class
}

private val serviceModule = module {
    single { InformationAccessRulesServiceImpl(
    get(), get(), get(), get(), get(),
    get(named(AccessMappersQualifiers.ACCESS_RULE_TO_BASE_MODEL_MAPPER)))
    } bind InformationAccessRulesService::class
    single { InformationUserRolesServiceImpl(
        get(), get(),
        get(named(AccessMappersQualifiers.USER_ROLE_ENTITY_TO_BASE_MODEL_MAPPER)))
    } bind InformationUserRolesService::class
    single { InteractionAccessRulesServiceImpl(
        get(), get(), get(), get(), get(),
        get(named(AccessMappersQualifiers.ACCESS_RULE_TO_BASE_MODEL_MAPPER)),
        get(named(AccessMappersQualifiers.CREATE_RULE_MODEL_TO_ENTITY_MAPPER)),
        get(named(AccessMappersQualifiers.CREATE_ROLE_RULE_MODEL_TO_ENTITY_MAPPER)),
        get(named(AccessMappersQualifiers.CREATE_USER_RULE_MODEL_TO_ENTITY_MAPPER)),
    ) } bind InteractionAccessRulesService::class
}

val accessModule = module {
    includes(mappersModule)
    includes(repositoryModule)
    includes(componentsModule)
    includes(pluginModule)
    includes(serviceModule)
}