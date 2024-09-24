package bingsoochef.bingsoochef.bingsoo.application

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.bingsoo.persistence.BingsooRepository
import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.BingsooError
import bingsoochef.bingsoochef.common.exception.code.UserError
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
            .orElseThrow{ BingsooException(UserError.USER_NOT_FOUND) }

        val bingsoo: Bingsoo = bingsooRepository.save(Bingsoo(taste = command.taste))
        // TODO("User에 Bingsoo 매핑")

        return bingsoo
    }

    fun updateBingsoo(command: UpdateBingsooCommand): Bingsoo {
        val bingsoo = userRepository.findById(command.userId)
            .orElseThrow{ BingsooException(UserError.USER_NOT_FOUND) }
            .bingsoo ?: throw BingsooException(BingsooError.NOT_FOUND, "사용자에게 빙수가 없습니다.")

        bingsoo.updateTaste(taste = command.taste)

        return bingsoo
    }

    @Transactional(readOnly = true)
    fun getBingsoo(bingsooId: Long): Bingsoo {
        return bingsooRepository.findById(bingsooId)
            .orElseThrow{ BingsooException(BingsooError.NOT_FOUND) }
    }
}