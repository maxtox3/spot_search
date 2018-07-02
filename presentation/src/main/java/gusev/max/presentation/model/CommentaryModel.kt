package gusev.max.presentation.model

/**
 * Created by v on 27/06/2018.
 */
data class CommentaryModel(
    val id: Long,
    val eventId: Long,
    val userId: Long,
    val avatar: String,
    val text: String,
    val createdAt: String,
    val updatedAt: String
) : BaseModel