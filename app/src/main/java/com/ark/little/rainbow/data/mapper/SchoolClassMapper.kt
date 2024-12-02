package com.ark.little.rainbow.data.mapper

import com.ark.little.rainbow.data.model.dto.SchoolClassDto
import com.ark.little.rainbow.domain.model.SchoolClass

object SchoolClassMapper {

    fun SchoolClassDto.toSchoolClass(): SchoolClass {
        return SchoolClass(
            id = this.id,
            name = this.name,
            createdAt = this.createdAt,
            feeAmount = this.feeAmount
        )
    }

    fun SchoolClass.toSchoolClassDto(): SchoolClassDto {
        return SchoolClassDto(
            id = this.id,
            name = this.name,
            createdAt = this.createdAt,
            feeAmount = this.feeAmount
        )
    }
}