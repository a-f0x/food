package ru.f0x.food.services.food

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.repository.FoodRepository
import ru.f0x.food.services.IDateTimeService

@Service
class FoodProductsService(
        private val foodRepository: FoodRepository,
        private val mapper: FoodMapper,
        private val dateTimeService: IDateTimeService
) : IFoodProductsService {

    override fun findByName(name: String): List<FoodProductDTO> = foodRepository.findAllByName(name)
            .map {
                mapper.mapFromEntity(it)
            }

    override fun findByManufacturer(manufacturer: String): List<FoodProductDTO> =
            foodRepository.findAllByManufacturer(manufacturer)
                    .map {
                        mapper.mapFromEntity(it)
                    }

    @Transactional
    override fun add(user: CustomUserDetails, foodProduct: FoodProductDTO): FoodProductDTO {
        val currentTime = getCurrentTime()
        return mapper.mapFromEntity(

                foodRepository.save(
                        mapper.mapFromDTO(foodProduct).apply {
                            created = currentTime
                            modified = currentTime
                            createdByuserId = user.id
                            modifiedByuserId = user.id
                        }
                )
        )
    }

    @Transactional
    override fun addAll(user: CustomUserDetails, foodProducts: List<FoodProductDTO>): List<FoodProductDTO> {
        val currentTime = getCurrentTime()
        return foodRepository.saveAll(
                foodProducts.map {
                    mapper.mapFromDTO(it).apply {
                        created = currentTime
                        modified = currentTime
                        createdByuserId = user.id
                        modifiedByuserId = user.id
                    }
                })
                .map {
                    mapper.mapFromEntity(it)
                }
    }

    override fun getAll(): List<FoodProductDTO> = foodRepository.findAll().map { mapper.mapFromEntity(it) }

    override fun update(user: CustomUserDetails, foodProductDTO: FoodProductDTO): FoodProductDTO {

        return mapper.mapFromEntity(
                foodRepository.save(
                        mapper.mapFromDTO(foodProductDTO).apply {
                            modified = getCurrentTime()
                            modifiedByuserId = user.id
                        }
                )
        )
    }


    override fun delete(ids: List<Int>) {
        foodRepository.deleteAllByIdIn(ids)
    }

    private fun getCurrentTime() = dateTimeService.getCurrentTime()
}