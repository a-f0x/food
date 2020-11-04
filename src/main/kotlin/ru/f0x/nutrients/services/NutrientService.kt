package ru.f0x.nutrients.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.nutrients.models.dto.NutrientDTO
import ru.f0x.nutrients.repository.NutrientRepository

@Service
class NutrientService(
        private val nutrientRepository: NutrientRepository,
        private val mapper: NutrientMapper
) : INutrientService {

    override fun findByName(name: String): List<NutrientDTO> = nutrientRepository.findAllByName(name)
            .map {
                mapper.mapFromEntity(it)
            }

    override fun findByManufacturer(manufacturer: String): List<NutrientDTO> =
            nutrientRepository.findAllByManufacturer(manufacturer)
                    .map {
                        mapper.mapFromEntity(it)
                    }

    @Transactional
    override fun add(nutrient: NutrientDTO): NutrientDTO = mapper.mapFromEntity(
            nutrientRepository.save(
                    mapper.mapFromDTO(nutrient)
            )
    )

    @Transactional
    override fun addAll(nutrients: List<NutrientDTO>): List<NutrientDTO> =
            nutrientRepository.saveAll(nutrients.map { mapper.mapFromDTO(it) })
                    .map {
                        mapper.mapFromEntity(it)
                    }
}