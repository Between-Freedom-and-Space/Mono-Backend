package com.between_freedom_and_space.mono_backend.access.service.models

data class RuleCheckResult(
    val checkResult: CheckResult,
    val rule: BaseAccessRuleModel?,
) {

    enum class CheckResult {
        HAS_ACCESS, NO_ACCESS;
    }
}
