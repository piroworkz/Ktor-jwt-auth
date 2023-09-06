package davidluna.com.testshared

import davidluna.com.domain.*

val fakeAuthRequest = AuthRequest(
    "voxel.mail@gmail.com",
    "KSJDIWRNM(/&%"
)

val fakeUser = User(
    username = "voxel.mail@gmail.com",
    password = "2c7b49724febc4086ea55eee69efe42f67a194220fed879616ae02aa310cc9ef",
    salt = "cad5df1285dc59b8028d8b7fe06b5c7d70172d417972033527f7b87afd0c9f05",
    role = Role.USER
)

val fakeSuccessCode = StatusCode(
    value = 200,
    description = "OK"
)

//val fakeFailCode = StatusCode(
//    value = 400,
//    description = "Bad Request"
//)

val fakeSuccessUserResponse = Response(
    code = fakeSuccessCode,
    message = fakeSuccessCode.description,
    token = "FAKE JWT TOKEN",
    body = fakeUser
)


val fakeSuccessBooleanResponse = Response(
    code = fakeSuccessCode,
    message = fakeSuccessCode.description,
    token = "FAKE JWT TOKEN",
    body = true
)


//val fakeFailureResponse = Response<String>(
//    code = fakeFailCode,
//    message = fakeFailCode.description,
//    token = "FAKE JWT TOKEN",
//    body = ""
//)

val fakeSaltedHash = SaltedHash(
    hash = fakeUser.password,
    salt = fakeUser.salt
)

