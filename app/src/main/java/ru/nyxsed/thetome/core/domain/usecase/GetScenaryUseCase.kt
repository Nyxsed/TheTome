package ru.nyxsed.thetome.core.domain.usecase

import ru.nyxsed.thetome.core.data.SceneryProvider
import ru.nyxsed.thetome.core.domain.models.Scenery
import javax.inject.Inject

class GetListSceneryUseCase @Inject constructor() {
    operator fun invoke(): List<Scenery> {
        return SceneryProvider.all
    }
}