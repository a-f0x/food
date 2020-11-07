package ru.f0x.nutrients.services.nutrients

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.nutrients.models.dto.nutrients.NutrientDTO
import ru.f0x.nutrients.repository.NutrientRepository
import ru.f0x.nutrients.services.IDateTimeService

@Service
class NutrientService(
        private val nutrientRepository: NutrientRepository,
        private val mapper: NutrientMapper,
        private val dateTimeService: IDateTimeService
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
    override fun add(nutrient: NutrientDTO): NutrientDTO {
        val currentTime = getCurrentTime()
        return mapper.mapFromEntity(

                nutrientRepository.save(
                        mapper.mapFromDTO(nutrient).apply {
                            created = currentTime
                            modified = currentTime
                        }
                )
        )
    }

    @Transactional
    override fun addAll(nutrients: List<NutrientDTO>): List<NutrientDTO> {
        val currentTime = getCurrentTime()
        return nutrientRepository.saveAll(
                nutrients.map {
                    mapper.mapFromDTO(it).apply {
                        created = currentTime
                        modified = currentTime
                    }
                })
                .map {
                    mapper.mapFromEntity(it)
                }
    }

    override fun getAll(): List<NutrientDTO> = nutrientRepository.findAll().map { mapper.mapFromEntity(it) }

    override fun update(nutrientDTO: NutrientDTO): NutrientDTO {

        return mapper.mapFromEntity(
                nutrientRepository.save(
                        mapper.mapFromDTO(nutrientDTO).apply {
                            modified = getCurrentTime()
                        }
                )
        )
    }


    override fun delete(ids: List<Int>) {
        nutrientRepository.deleteAllByIdIn(ids)
    }

    private fun getCurrentTime() = dateTimeService.getCurrentTime()
}