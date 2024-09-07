package bingsoochef.bingsoochef.bingsoo.application

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.bingsoo.persistence.BingsooRepository
import bingsoochef.bingsoochef.global.error.NotFoundException
import bingsoochef.bingsoochef.user.domain.User
import bingsoochef.bingsoochef.user.persistence.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BingsooService(
    var userRepository: UserRepository,
    var bingsooRepository: BingsooRepository
) {

    fun createBingsoo(command: CreateBingsooCommand): Bingsoo {
        var user: User = userRepository.findById(command.userId)
            .orElseThrow { NotFoundException("존재하지 않는 사용자입니다.") }

        val bingsoo: Bingsoo = bingsooRepository.save(Bingsoo(taste = command.taste))
        // TODO("User에 Bingsoo 매핑")

        return bingsoo
    }

    fun updateBingsoo(command: UpdateBingsooCommand): Bingsoo {
        val bingsoo = userRepository.findById(command.userId)
            .orElseThrow { NotFoundException("존재하지 않는 사용자입니다.") }
            .bingsoo ?: throw NotFoundException("존재하지 않는 빙수입니다.")

        bingsoo.updateTaste(taste = command.taste)

        return bingsoo
    }

    @Transactional(readOnly = true)
    fun getBingsoo(bingsooId: Long): Bingsoo {
        return bingsooRepository.findById(bingsooId)
            .orElseThrow { NotFoundException("존재하지 않는 빙수입니다.") }
    }
}