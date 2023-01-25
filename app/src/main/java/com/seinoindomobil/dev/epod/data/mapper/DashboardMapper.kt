package com.seinoindomobil.dev.epod.data.mapper

import com.seinoindomobil.dev.epod.data.remote.dashboard.dto.DashboardDTO
import com.seinoindomobil.dev.epod.domain.model.Dashboards

fun DashboardDTO.toDashboardDomain() : Dashboards{
  return Dashboards(
      title = result.banner.map { it.text },
      count = result.banner.map { it.count },
      icon = result.banner.map { it.icon }
  )
}