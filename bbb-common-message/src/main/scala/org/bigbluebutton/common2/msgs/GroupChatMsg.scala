package org.bigbluebutton.common2.msgs

object GroupChatAccess {
  val PUBLIC = "PUBLIC_ACCESS"
  val PRIVATE = "PRIVATE_ACCESS"
}

object GroupChatMessageType {
  val DEFAULT = "default"
  val API = "api"
  val PRESENTATION = "presentation"
  val POLL = "poll"
  val BREAKOUTROOM_MOD_MSG = "breakoutRoomModeratorMsg"
  val PUBLIC_CHAT_HIST_CLEARED = "publicChatHistoryCleared"
  val USER_AWAY_STATUS_MSG = "userAwayStatusMsg"
}

case class GroupChatUser(id: String, name: String = "", role: String = "VIEWER")
case class GroupChatMsgFromUser(correlationId: String, sender: GroupChatUser, message: String)
case class GroupChatMsgToUser(id: String, timestamp: Long, correlationId: String, sender: GroupChatUser, chatEmphasizedText: Boolean = false, message: String)
case class GroupChatInfo(id: String, access: String, createdBy: GroupChatUser, users: Vector[GroupChatUser])

object OpenGroupChatWindowReqMsg { val NAME = "OpenGroupChatWindowReqMsg" }
case class OpenGroupChatWindowReqMsg(header: BbbClientMsgHeader, body: OpenGroupChatWindowReqMsgBody) extends StandardMsg
case class OpenGroupChatWindowReqMsgBody()

object OpenGroupChatWindowEvtMsg { val NAME = "OpenGroupChatWindowEvtMsg" }
case class OpenGroupChatWindowEvtMsg(header: BbbClientMsgHeader, body: OpenGroupChatWindowEvtMsgBody) extends StandardMsg
case class OpenGroupChatWindowEvtMsgBody(chatWindowId: String, openedBy: String)

object GetGroupChatsReqMsg { val NAME = "GetGroupChatsReqMsg" }
case class GetGroupChatsReqMsg(header: BbbClientMsgHeader, body: GetGroupChatsReqMsgBody) extends StandardMsg
case class GetGroupChatsReqMsgBody()

object GetGroupChatsRespMsg { val NAME = "GetGroupChatsRespMsg" }
case class GetGroupChatsRespMsg(header: BbbClientMsgHeader, body: GetGroupChatsRespMsgBody) extends BbbCoreMsg
case class GetGroupChatsRespMsgBody(chats: Vector[GroupChatInfo])

object GetGroupChatMsgsReqMsg { val NAME = "GetGroupChatMsgsReqMsg" }
case class GetGroupChatMsgsReqMsg(header: BbbClientMsgHeader, body: GetGroupChatMsgsReqMsgBody) extends StandardMsg
case class GetGroupChatMsgsReqMsgBody(chatId: String)

object GetGroupChatMsgsRespMsg { val NAME = "GetGroupChatMsgsRespMsg" }
case class GetGroupChatMsgsRespMsg(header: BbbClientMsgHeader, body: GetGroupChatMsgsRespMsgBody) extends BbbCoreMsg
case class GetGroupChatMsgsRespMsgBody(chatId: String, msgs: Vector[GroupChatMsgToUser])

object CreateGroupChatReqMsg { val NAME = "CreateGroupChatReqMsg" }
case class CreateGroupChatReqMsg(header: BbbClientMsgHeader, body: CreateGroupChatReqMsgBody) extends StandardMsg
case class CreateGroupChatReqMsgBody(correlationId: String, access: String,
                                     users: Vector[String], msg: Vector[GroupChatMsgFromUser])

object GroupChatCreatedEvtMsg { val NAME = "GroupChatCreatedEvtMsg" }
case class GroupChatCreatedEvtMsg(header: BbbClientMsgHeader, body: GroupChatCreatedEvtMsgBody) extends BbbCoreMsg
case class GroupChatCreatedEvtMsgBody(correlationId: String, chatId: String, createdBy: GroupChatUser,
                                      access: String,
                                      users:  Vector[GroupChatUser], msg: Vector[GroupChatMsgToUser])

object DestroyGroupChatReqMsg { val NAME = "DestroyGroupChatReqMsg" }
case class DestroyGroupChatReqMsg(header: BbbClientMsgHeader, body: DestroyGroupChatReqMsgBody) extends StandardMsg
case class DestroyGroupChatReqMsgBody(chats: Vector[String])

object GroupChatDestroyedEvtMsg { val NAME = "GroupChatDestroyedEvtMsg" }
case class GroupChatDestroyedEvtMsg(header: BbbClientMsgHeader, body: GroupChatDestroyedEvtMsgBody) extends BbbCoreMsg
case class GroupChatDestroyedEvtMsgBody(requesterId: String, chatId: String)

object ChangeGroupChatAccessReqMsg { val NAME = "ChangeGroupChatAccessReqMsg" }
case class ChangeGroupChatAccessReqMsg(header: BbbClientMsgHeader, body: ChangeGroupChatAccessReqMsgBody) extends StandardMsg
case class ChangeGroupChatAccessReqMsgBody(chatId: String, publicChat: Boolean)

object GroupChatAccessChangedEvtMsg { val NAME = "GroupChatAccessChangedEvtMsg" }
case class GroupChatAccessChangedEvtMsg(header: BbbClientMsgHeader, body: GroupChatAccessChangedEvtMsgBody) extends BbbCoreMsg
case class GroupChatAccessChangedEvtMsgBody(requesterId: String, chatId: String, public: Boolean)

object GroupChatAddUserReqMsg { val NAME = "GroupChatAddUserReqMsg" }
case class GroupChatAddUserReqMsg(header: BbbClientMsgHeader, body: GroupChatAddUserReqMsgBody) extends StandardMsg
case class GroupChatAddUserReqMsgBody(chats: Vector[String])

object GroupChatUserAddedEvtMsg { val NAME = "GroupChatUserAddedEvtMsg" }
case class GroupChatUserAddedEvtMsg(header: BbbClientMsgHeader, body: GroupChatUserAddedEvtMsgBody) extends BbbCoreMsg
case class GroupChatUserAddedEvtMsgBody(requesterId: String, chats: Vector[GroupChatUser])

object GroupChatRemoveUserReqMsg { val NAME = "GroupChatRemoveUserReqMsg" }
case class GroupChatRemoveUserReqMsg(header: BbbClientMsgHeader, body: GroupChatRemoveUserReqMsgBody) extends StandardMsg
case class GroupChatRemoveUserReqMsgBody(chats: Vector[String])

object GroupChatUserRemovedEvtMsg { val NAME = "GroupChatUserRemovedEvtMsg" }
case class GroupChatUserRemovedEvtMsg(header: BbbClientMsgHeader, body: GroupChatUserRemovedEvtMsgBody) extends BbbCoreMsg
case class GroupChatUserRemovedEvtMsgBody(requesterId: String, chats: Vector[String])

object SendGroupChatMessageMsg { val NAME = "SendGroupChatMessageMsg" }
case class SendGroupChatMessageMsg(header: BbbClientMsgHeader, body: SendGroupChatMessageMsgBody) extends StandardMsg
case class SendGroupChatMessageMsgBody(chatId: String, msg: GroupChatMsgFromUser)

object SendGroupChatMessageFromApiSysPubMsg { val NAME = "SendGroupChatMessageFromApiSysPubMsg" }
case class SendGroupChatMessageFromApiSysPubMsg(
    header: BbbClientMsgHeader,
    body:   SendGroupChatMessageFromApiSysPubMsgBody
) extends StandardMsg
case class SendGroupChatMessageFromApiSysPubMsgBody(
    userName: String,
    message:  String
)

object GroupChatMessageBroadcastEvtMsg { val NAME = "GroupChatMessageBroadcastEvtMsg" }
case class GroupChatMessageBroadcastEvtMsg(header: BbbClientMsgHeader, body: GroupChatMessageBroadcastEvtMsgBody) extends BbbCoreMsg
case class GroupChatMessageBroadcastEvtMsgBody(chatId: String, msg: GroupChatMsgToUser)

object UserTypingPubMsg { val NAME = "UserTypingPubMsg" }
case class UserTypingPubMsg(header: BbbClientMsgHeader, body: UserTypingPubMsgBody) extends StandardMsg
case class UserTypingPubMsgBody(chatId: String)

object SetGroupChatVisibleReqMsg { val NAME = "SetGroupChatVisibleReqMsg" }
case class SetGroupChatVisibleReqMsg(header: BbbClientMsgHeader, body: SetGroupChatVisibleReqMsgBody) extends StandardMsg
case class SetGroupChatVisibleReqMsgBody(chatId: String, visible: Boolean)

object SetGroupChatLastSeenReqMsg { val NAME = "SetGroupChatLastSeenReqMsg" }
case class SetGroupChatLastSeenReqMsg(header: BbbClientMsgHeader, body: SetGroupChatLastSeenReqMsgBody) extends StandardMsg
case class SetGroupChatLastSeenReqMsgBody(chatId: String, lastSeenAt: String)
