package com.bn.taipeitravelinfo.data.model.result

import com.bn.taipeitravelinfo.data.model.Attraction

data class AttractionsResult(
    override val total: Int,
    override val data: List<Attraction>
): BaseResult()