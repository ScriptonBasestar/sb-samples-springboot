package sample.app.web.board

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sample.core.exception.DataNotFoundException
import sample.domain.jpa.repository.CommentRepository

@Service
@Transactional(readOnly = true)
class CommentService(
    @Autowired private val commentRepository: CommentRepository,
) {

    fun list(pageable: Pageable) =
        commentRepository.findAll(pageable)

    fun read(id: Long) = commentRepository.findById(id).orElseThrow {
        DataNotFoundException("data not found exception")
    }

    @Transactional
    fun modify(id: Long, commentReqDto: CommentReqDto) {
        val commentEntity =
            commentRepository.findById(id).orElseThrow { DataNotFoundException("data not found exception") }
        commentReqDto.apply {
            content?.let {
                commentEntity.content = it
            }
        }
    }

    @Transactional
    fun remove(id: Long) = commentRepository.deleteById(id)
}
