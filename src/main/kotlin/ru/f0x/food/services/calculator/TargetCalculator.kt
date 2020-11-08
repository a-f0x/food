package ru.f0x.food.services.calculator

import ru.f0x.food.models.dto.calculator.CalculationResult
import ru.f0x.food.models.dto.calculator.GainTargetCalculator
import ru.f0x.food.models.dto.calculator.NutrientDTO
import ru.f0x.food.models.dto.calculator.NutrientsRange
import ru.f0x.food.models.entity.*

/**
 * РАССЧЕТ ОСНОВНОГО ОБМЕНА (ОО)
 *
 * для мужчин: 10 × вес (кг) + 6,25 × рост (см) – 5 × возраст (г) + 5
 * для женщин: 10 × вес (кг) + 6,25 × рост (см) – 5 × возраст (г) – 161
 *
 *  Полученный результат – величина вашего основного обмена. Определив величину ОО, можно вычислить,
 *  сколько ккал в сутки вам требуется для поддержания тела при том или ином уровне нагрузки.
 *  Для этого ОО нужно умножить на коэффициент физической активности (у всех он разный):
 *
 * 1,2, – для малоподвижных людей (тренировок мало, они низкой интенсивности или вообще отсутствуют);
 * 1,375 – для людей с низкой активностью (легкие тренировки 1–3 раза в неделю);
 * 1,55 – для умеренно активных людей (работа средней тяжести либо тренировки умеренной интенсивности 3–5 дней в неделю);
 * 1,7 – для активных людей (физическая работа плюс тренировки либо интенсивные тренировки 6–7 раз в неделю);
 * 1,9 – для предельно активных людей (физическая работа плюс очень интенсивные занятия спортом).
 * Полученная величина – ваша суточная потребность в ккал.
 * На основании этой цифры можно рассчитать нужную калорийность вашего рациона.
 *
// * Для поддержания вашего сегодняшнего веса
// *
// * Поступление калорий должно равняться рассчитанным затратам. Например, ваш ОО равен 1300 ккал,
// * суточная потребность 1887 ккал, ваш рацион должен составлять 1887 ккал.
// *
// * Для снижения веса
// *
// * Необходимо создать дефицит ккал примерно на 10–30% меньше суточного рациона (в зависимости от вашего текущего веса).
// * Например, ваш ОО равен 1300 ккал, суточная потребность 1887 ккал (коэффициент 1,3: тренировки 1–3 раза в неделю),
// * ваш рацион для снижения веса будет равен 1500 ккал (уменьшили на 20%).
// * Необходимо помнить, что полученная калорийность не должна быть ниже ОО,
// * в данном случае ниже 1300 ккал.
// * Так как организм каждый день не будет получать в полном объеме то минимальное количество энергии,
// * которое необходимо для обеспечения нормальной жизнедеятельности.
// * В результате все обменные процессы организма будут замедляться.
// *
// * Для увеличения мышечной массы, а также для беременных
// *
// * Необходим профицит калорий. Например, для того чтобы набрать недостающий вес,
// * излишек энергии будет составлять около 200 ккал/день (это как пример, все индивидуально).
// *
// * РАССЧЕТ БЖУ
// * Для поддержания текущего веса:
// *
// * белки > 14% от рациона (1–1,5 г белка на килограмм веса);
// * жиры – 26–30% (1–1,2 г/кг);
// * углеводы – 56–60% (6–6,5 г/кг).
// *
// * Для увеличения массы тела:
// *
// * белки > 18% от рациона (2–2,5 г белка на килограмм веса);
// * жиры – 26–30%  (0,8-1,0 г/кг);
// * углеводы > 56% (6,5–7 г/кг).
// *
// * Для снижения массы тела:
// *
// * белки > 18% от рациона (1,2–1,8 г белка на килограмм веса);
// * жиры < 26%  (0,7-0,8 г/кг);
// * углеводы < 56% (2–3,5 г/кг).
 *
 * */

class TargetCalculator(
        private val weight: Float,
        private val height: Float,
        private val age: Int,
        private val activity: ActivityEnum,
        private val sex: SexEnum
) : ITargetCalculator {

    private companion object {
        private const val WEIGHT_RATE = 10F
        private const val HEIGHT_RATE = 6.25F
        private const val AGE_RATE = 5F
        private const val FEMALE_CONST = -161F
        private const val MALE_CONST = 5F
    }

    override fun calculate(): CalculationResult {
        return GainTargetCalculator(
                basicMetabolismKCal = calculateBasicMetabolismKCal(),
                activity = activity,
                carbohydrates = NutrientsRange(
                        min = NutrientDTO(Carbohydrate, 3.1f * weight),
                        max = NutrientDTO(Carbohydrate, 4.9f * weight),
                ),
                fats = NutrientsRange(
                        min = NutrientDTO(Fat, 0.54f * weight),
                        max = NutrientDTO(Fat, 0.87f * weight)
                ),
                proteins = NutrientsRange(
                        min = NutrientDTO(Protein, 1.72f * weight),
                        max = NutrientDTO(Protein, 2.72f * weight)
                )
        ).calculate()

    }

    private fun calculateBasicMetabolismKCal(): Int = (calculateMinimumBasicMetabolismKCal() * getActivityRate()).toInt()

    private fun calculateMinimumBasicMetabolismKCal(): Int = when (sex) {
        SexEnum.MALE -> calculate(MALE_CONST)
        SexEnum.FEMALE -> calculate(FEMALE_CONST)
    }

    private fun calculate(sexConst: Float): Int {
        return (
                ((WEIGHT_RATE * weight) + (HEIGHT_RATE * height) - (AGE_RATE * age) + sexConst)).toInt()
    }

    private fun getActivityRate(): Float = when (activity) {
        ActivityEnum.MINIMUM -> 1.2f
        ActivityEnum.WEAK -> 1.375f
        ActivityEnum.MEDIUM -> 1.55f
        ActivityEnum.HIGH -> 1.7f
        ActivityEnum.EXTRA_HIGH -> 1.9f
    }

}