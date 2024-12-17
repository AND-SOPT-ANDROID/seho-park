package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.HomeCommonContent
import org.sopt.and.domain.entity.HomeContent

interface DummyHomeRepository {
    fun getDummyMainContents(): List<HomeContent>
    fun getDummyCommonContents(): List<HomeCommonContent>
    fun getDummyRankingContents(): HomeCommonContent
}