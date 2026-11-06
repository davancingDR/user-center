# user-center 项目交接文档

> **给下一个接手的模型/会话看的。** 读完本文你就能立刻接上上下文，继续陪用户往下做。
> **最后更新：2026-07-06**

---

## 0. 一分钟速览

- **项目性质**：用户的**个人学习/练手项目**（Spring Boot 用户中心后端），不是生产系统。
- **用户身份**：正在通过亲手写代码学习 Java 后端。
- **合作模式（最重要）**：**你不要直接改代码**。你的产出是"完整代码样例 + 为什么这样改 + 优劣势 + 常见坑"。用户自己去 IDE 里敲。
- **交流语言**：中文。
- **当前所处阶段**：架构重构 + 全局异常处理（作业已下发，等用户改完汇报）。
- **下一站**：登录功能（认证 + Session/Token）。

---

## 1. 协作方式（每次回复前默念）

1. **不改代码**。给样例、讲原理、指优劣、点常见坑。动手是用户的事——代替他敲就是剥夺学习机会。
2. **给完整可抄的代码样例**，不要"你就把这里改一下"这种模糊描述。
3. **每一步讲清楚 Why**：为什么这样设计、为什么这个顺序、为什么这里要小心。这才是他要学的东西。
4. **顺手指出改进点**（魔法数字、裸类型、命名等），但明确标注"不急着改，建立意识"，不要打断主线。
5. **用户做完一步 → 你先核对他的实现 → 再下一步**。不要一次推很多步导致他做完你都没跟。
6. 涉及跨代升级（Spring Boot 2→3、JDK 版本）时提醒他：**编译通过 ≠ 能跑起来**，运行时问题才是重点。

**用户已经明确同意本协作方式**，不需要每次确认。

---

## 2. 技术栈 & 项目基本信息

| 项 | 值 |
|---|---|
| 语言/JDK | Java 21 |
| 框架 | Spring Boot 3.5.x |
| ORM | MyBatis-Plus |
| 密码加密 | spring-security-crypto（BCrypt，**不是** spring-boot-starter-security，接口不会被拦截） |
| API 文档 | knife4j → http://localhost:10086/doc.html |
| 数据库表 | `user`（字段见 `entity/User.java`） |
| 启动命令 | `mvn spring-boot:run` |
| 端口 | 10086 |
| Git 分支 | master |
| 当前提交 | `ff08fd3 feat: 1、注册功能业务实现 2、新增用户角色、账号状态枚举 3、用户实体类字段名调整 3、重复错误码修正` |

---

## 3. 目录 & 关键类速览

```
src/main/java/com/rqh/user/
├── UserApplication.java              启动类
├── base/
│   ├── BaseResponse.java             ⚠️ 即将废弃（本轮重构会删）
│   ├── BizResponse.java              ⚠️ 即将废弃
│   └── BaseQueryPage.java            分页请求基类（保留）
├── config/
│   ├── SwaggerConfig.java            knife4j 配置
│   └── PasswordConfig.java           BCryptPasswordEncoder Bean（已完成）
├── controller/
│   ├── UserController.java           前台 /user/*（本轮改成 /api/user/*）
│   └── UserManageController.java     后台 /user/*（本轮改成 /api/admin/user/*，可能改名 UserAdminController）
├── service/
│   ├── UserService.java
│   └── impl/UserServiceImpl.java     ★ 注册逻辑在此，本轮要瘦身
├── manager/
│   ├── UserManager.java              目前只有分页查询用到
│   └── impl/UserManagerImpl.java
├── mapper/UserMapper.java
├── exception/
│   ├── BizException.java             ⚠️ 即将废弃
│   ├── UserException.java            ⚠️ 即将废弃
│   └── GlobalExceptionHandler.java   ⚠️ 空壳/旧版，本轮会挪到 common/exception/
├── model/
│   ├── domain/
│   │   ├── dto/                      请求 DTO
│   │   ├── vo/                       响应 VO
│   │   └── entity/User.java
│   └── enums/
│       ├── UserRoleEnum.java         已完成（0-管理员 1-普通 2-vip）
│       ├── AccountStatusEnum.java    已完成（0-禁用 1-正常）
│       ├── UserExceptionEnum.java    ⚠️ 即将废弃（拆成 CommonErrorCode + UserErrorCode）
│       ├── ResponseCodeEnum.java     ⚠️ 即将废弃
│       ├── ResultCodeEnum.java       ⚠️ 即将废弃（接口，被 IErrorCode 取代）
│       └── ExceptionCodeEnum.java    ⚠️ 即将废弃
├── common/                           ★ 本轮要新建的公共层
│   └── errorcode/IErrorCode.java     用户已经先建了空壳
└── utils/
    ├── CopyUtils.java                实体拷贝工具（保留）
    └── dic/                          ⚠️ 死代码，本轮 Safe Delete
```

**⚠️ 标注即将废弃的类，在用户完成本轮作业后统一删除。核对他实现时按新架构核对，不要按旧类。**

---

## 4. 记忆系统（`C:\Users\ruanqinhui\.claude\projects\D--wechatProgram-user-center\memory\`）

已存三条：

- `MEMORY.md` — 索引
- `user-mentoring-mode.md`（feedback）— 导师模式约定
- `project-user-center-roadmap.md`（project）— 路线图与进度

新会话记得先 Read `MEMORY.md`（自动加载）。有新的 feedback / project 状态变化时**更新已有记忆**，别叠新的。

---

## 5. 已完成的工作

### 5.1 版本升级（提交 `5ca98c9`）
- JDK 21 + Spring Boot 3.5.x
- 关键坑：Maven Compiler Plugin 的 `annotationProcessorPaths` 里 lombok 版本不能为空
- 关键坑：SB2 → SB3，`springfox` 换成 `springdoc-openapi` + `knife4j-openapi3-jakarta`
- 关键坑：`javax.*` → `jakarta.*`（`@Resource`、`@Valid` 等）
- 冒烟测试：应用启动、doc.html 可打开、启动日志无 WARN

### 5.2 注册功能（提交 `ff08fd3`）
- BCrypt 加密（`PasswordConfig` + `PasswordEncoder` 注入）
- 用户角色 / 账号状态 用枚举替代魔法数字
- `UserExceptionEnum` 里重复错误码修正
- 落库后返回自增主键
- 用户已自测通过（能注册、能查重、数据库看到 `$2a$10$...` BCrypt 密文）

---

## 6. ★ 当前正在做的作业：架构重构 + 全局异常处理

**用户已同意方向**，作业单已下发，**等用户改完汇报**。

### 6.1 用户提的两个原始问题（决定了本轮方向）
1. 现在的项目架构如果有问题，怎么改更贴近生产？
2. `BaseResponse`/`BizResponse` 有必要继承吗？异常和 `ResultCodeEnum` 接口有必要吗？

### 6.2 你上一轮给的回答核心结论
- **过度设计**：`BaseResponse` + `BizResponse` 继承没意义，一个 `Result<T>` 够了；`BizException` + `UserException` 双层继承也没意义，一个 `BusinessException` + 错误码枚举携带模块信息即可。
- **IErrorCode 接口是必要的**：全局异常处理器只依赖接口，加新模块（订单、支付）时公共代码零改动——这是接口存在的**唯一**理由。
- **架构建议**：目前是"技术分层扁平结构"（controller/service/manager/mapper 各自一大坨），生产项目通常按**业务模块**再切一刀（`modules/user/*`、`modules/order/*`），每个模块内部再分层。**本轮先不做模块化重构**，只做公共层抽取；等第二个业务模块出现时再顺势切分。

### 6.3 完整作业单（用户手上有）

**Step 1 — 新建 `common` 包**（以下 5 个新文件）

```
common/
├── errorcode/
│   ├── IErrorCode.java           接口: getCode() / getMsg()
│   └── CommonErrorCode.java      枚举: SUCCESS/SYSTEM_ERROR/PARAMETER_ERROR/UNAUTHORIZED/FORBIDDEN
├── response/Result.java          统一响应体, 取代 BaseResponse+BizResponse
└── exception/
    ├── BusinessException.java    携带 IErrorCode, 取代 BizException+UserException
    └── GlobalExceptionHandler.java  @RestControllerAdvice, 3 个处理器
```

三个异常处理器职责：
| 处理器 | 场景 | 日志级别 |
|---|---|---|
| `BusinessException` | 主动抛的预期业务失败 | `warn`，不打堆栈 |
| `BindException` | `@Valid` 失败（含 `MethodArgumentNotValidException`） | `warn` |
| `Exception` | 未知异常兜底 | `error` + 完整堆栈；返回给前端的话术要模糊（安全） |

⚠️ 不要 `@ExceptionHandler(Throwable.class)`——`Error`（OOM 等）不该被吞。

**Step 2 — 新建 `model/enums/UserErrorCode.java`**
- 只放用户业务错误：`ACCOUNT_EXIST("100001")` / `USER_NOT_EXIST("100002")` / `PASSWORD_WRONG("100003")` / `ACCOUNT_DISABLED("100004")`
- 编码规则：模块前缀 `10=用户` + 4 位业务码

**Step 3 — 改造 `UserServiceImpl`**
- 所有 `throw new UserException(UserExceptionEnum.XXX)` 换：
  - **参数格式类** → `throw new BusinessException(CommonErrorCode.PARAM_ERROR, "具体提示")`
  - **账号已存在** → `throw new BusinessException(UserErrorCode.ACCOUNT_EXIST)`
  - **系统错** → `throw new BusinessException(CommonErrorCode.SYSTEM_ERROR)`

**Step 4 — Controller 改造**
- 路由：`/user` → `/api/user`；`/user`(后台) → `/api/admin/user`
- `UserManageController` 类名建议 `Shift+F6` 重命名为 `UserAdminController`
- Controller 方法参数上加 `@Valid`（`import jakarta.validation.Valid`）
- 返回类型 `BizResponse<T>` → `Result<T>`

**Step 5 — 强化 `UserRegisterDTO` 校验**
- `account`: `@NotBlank` + `@Size(min=4)` + `@Pattern(regexp="^[^\\pP\\pS\\s]+$")`
- `password`/`checkPassword`: `@NotBlank` + `@Size(min=8)`
- Service 里对应的长度/字符校验**全部删掉**，只保留**跨字段（两次密码一致）+ 查库（账号查重）**

**Step 6 — Safe Delete 旧类**（新代码跑通后再删！）
删除：`BaseResponse` / `BizResponse` / `BizException` / `UserException` / 旧 `GlobalExceptionHandler` / `UserExceptionEnum` / `ResponseCodeEnum` / `ResultCodeEnum` / `ExceptionCodeEnum` / `utils/dic/*` 5 个
保留：`BaseQueryPage`（未来可能挪到 `common/model/`）

**Step 7 — 自测 6 个场景**
1. 启动无 WARN
2. `doc.html` 打得开，接口按新前缀出现
3. 正常注册 → `code=0` + 用户 id + 数据库看到 BCrypt 密文
4. 重复注册 → `code=100001` + `message=账号已存在`，控制台只有一行 warn 无堆栈
5. `@Valid` 生效 → userName 传空返回 `code=400`
6. 兜底：临时 `throw new RuntimeException("test")` → 返回 `code=500` 模糊话术，控制台有完整堆栈（测完删）

### 6.4 完整代码样例（防止你手抖）

<details>
<summary>点开：所有新建文件的完整代码</summary>

```java
// common/errorcode/IErrorCode.java
package com.rqh.user.common.errorcode;

public interface IErrorCode {
    String getCode();
    String getMsg();
}
```

```java
// common/errorcode/CommonErrorCode.java
package com.rqh.user.common.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements IErrorCode {
    SUCCESS("0",         "操作成功"),
    SYSTEM_ERROR("500",  "系统异常，请稍后重试"),
    PARAM_ERROR("400",   "参数错误"),
    UNAUTHORIZED("401",  "未登录或登录已过期"),
    FORBIDDEN("403",     "无权访问");
    private final String code;
    private final String msg;
}
```

```java
// common/response/Result.java
package com.rqh.user.common.response;

import com.rqh.user.common.errorcode.IErrorCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  private String code;
  private String message;
  private boolean success;
  private T data;

  private Result(String code, String message, boolean success, T data) {
    this.code = code;
    this.message = message;
    this.success = success;
    this.data = data;
  }

  public static <T> Result<T> success() {
    return success(null);
  }

  public static <T> Result<T> success(T data) {
    return new Result<>(CommonErrorCode.SUCCESS.getCode(),
            CommonErrorCode.SUCCESS.getMsg(), true, data);
  }

  public static <T> Result<T> fail(IErrorCode errorCode) {
    return new Result<>(errorCode.getCode(), errorCode.getMsg(), false, null);
  }

  public static <T> Result<T> fail(IErrorCode errorCode, String message) {
    return new Result<>(errorCode.getCode(), message, false, null);
  }
}
```

```java
// common/exception/BusinessException.java
package com.rqh.user.common.exception;

import com.rqh.user.common.errorcode.IErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final IErrorCode errorCode;
    public BusinessException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }
    public BusinessException(IErrorCode errorCode, String message) {
        super(message);
        this.errorCode = new IErrorCode() {
            @Override public String getCode() { return errorCode.getCode(); }
            @Override public String getMsg()  { return message; }
        };
    }
}
```

```java
// common/exception/GlobalExceptionHandler.java
package com.rqh.user.common.exception;

import com.rqh.user.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public Result<Void> handleBusiness(BusinessException e) {
    log.warn("业务异常: code={}, msg={}",
            e.getErrorCode().getCode(), e.getErrorCode().getMsg());
    return Result.fail(e.getErrorCode());
  }

  @ExceptionHandler(BindException.class)
  public Result<Void> handleBind(BindException e) {
    String msg = e.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining("; "));
    log.warn("参数校验失败: {}", msg);
    return Result.fail(CommonErrorCode.PARAM_ERROR, msg);
  }

  @ExceptionHandler(Exception.class)
  public Result<Void> handleException(Exception e) {
    log.error("系统异常", e);
    return Result.fail(CommonErrorCode.SYSTEM_ERROR);
  }
}
```

```java
// model/enums/UserErrorCode.java
package com.rqh.user.model.enums;

import com.rqh.user.common.errorcode.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements IErrorCode {
    ACCOUNT_EXIST("100001",     "账号已存在"),
    USER_NOT_EXIST("100002",    "用户不存在"),
    PASSWORD_WRONG("100003",    "账号或密码错误"),
    ACCOUNT_DISABLED("100004",  "账号已被禁用");
    private final String code;
    private final String msg;
}
```

```java
// UserServiceImpl.userRegister 改造后
@Override
public long userRegister(UserRegisterDTO userRegisterDTO) {
    // 格式校验交给 @Valid + DTO 注解, 这里只做跨字段和查库
    if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getCheckPassword())) {
        throw new BusinessException(CommonErrorCode.PARAM_ERROR, "两次输入的密码不一致");
    }
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("account", userRegisterDTO.getAccount());
    if (this.count(queryWrapper) > 0) {
        throw new BusinessException(UserErrorCode.ACCOUNT_EXIST);
    }
    String encryptPassword = passwordEncoder.encode(userRegisterDTO.getPassword());
    User user = new User();
    user.setUserName(userRegisterDTO.getUserName());
    user.setAccount(userRegisterDTO.getAccount());
    user.setPassword(encryptPassword);
    user.setUserRole(UserRoleEnum.COMMON.getCode());
    user.setAccountStatus(AccountStatusEnum.NORMAL.getCode());
    if (!this.save(user)) {
        throw new BusinessException(CommonErrorCode.SYSTEM_ERROR);
    }
    return user.getUserId();
}
```

```java
// UserRegisterDTO 强化
@Data
public class UserRegisterDTO {
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "账号不能为空")
    @Size(min = 4, message = "账号长度不能小于 4")
    @Pattern(regexp = "^[^\\pP\\pS\\s]+$", message = "账号不能包含特殊字符")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, message = "密码长度不能小于 8")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Size(min = 8, message = "确认密码长度不能小于 8")
    private String checkPassword;
}
```

```java
// UserController 改造后头部
@Tag(name = "用户接口")
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    // 所有方法参数上加 @Valid, 返回类型 Result<T>
    @PostMapping("/register")
    public Result<Long> userRegister(@Valid UserRegisterDTO dto) {
        return Result.success(userService.userRegister(dto));
    }
    // ...
}
```
</details>

### 6.5 用户接下来预期的动作

用户会：
1. 按 Step 1~5 改代码
2. 用 Safe Delete 走 Step 6（IDEA 右键 → Refactor → Safe Delete，会提示还有没有引用）
3. 跑 Step 7 六个自测场景
4. 回来汇报

**你（新模型）接手后要做**：
- 用户回来说"改完了"→ Read 相关文件，核对是否按新架构落地
- 用户说"跑通了"→ 进入下一站：**登录功能**
- 用户说"卡在 XXX"→ 定位问题、给修复思路（依然不动手）

---

## 7. 下一站预告：登录功能

用户跑通本轮后要做的事，预先记在这里：

- 校验 account/password（用 `passwordEncoder.matches` 而非 `equals`）
- 建立会话：Session（简单）or Token/JWT（更贴生产）——让用户自己选，讲清楚两条路的差异
- **一个已知的坑要提前打预防针**：`@RestControllerAdvice` **只覆盖进入 Controller 的请求**，Filter 阶段的异常它接不住。做登录拦截器（如 JWT 过滤器）时会撞上，到时候展开讲：可以用 `HandlerExceptionResolver` 或直接在 Filter 里 write 一个 `Result` JSON。
- 登录后，`/api/user/detail` `/api/user/edit/*` 才有真正的数据源

再往后：个人信息查询/修改（DTO/VO 分层、脱敏）→ 后台分页（`MybatisPlusInterceptor` + 实现 `queryUserInfoPage`）。

---

## 8. 遗留优化点（不阻塞主线，用户已知晓）

| 位置 | 问题 | 建议 |
|---|---|---|
| `UserServiceImpl` 查重 | `QueryWrapper` 用了裸类型，`"account"` 是魔法字符串 | 换 `LambdaQueryWrapper<User>().eq(User::getAccount, ...)` |
| `manager/` 层 | 目前 `UserManagerImpl` 只有分页查询用，价值不大 | 做后台分页时决定：要么充实要么合并进 Service |
| `create_time` / `update_time` | 目前手写 set 依赖数据库默认值 | 后期用 MyBatis-Plus `@TableField(fill=FieldFill.INSERT)` + `MetaObjectHandler` |

---

## 9. 环境 / 工具注意事项

- **Windows + Git Bash** 环境。文件路径用 **完整 Windows 绝对路径 + 反斜杠**（`D:\wechatProgram\user-center\...`），不要用 `/d/...` 或相对路径——Claude Code 的 Edit/Write 工具在这个环境下有 bug。
- **不要跑 `mvn spring-boot:run` 帮他跑**——启动是用户在 IDEA 里做的，你在 shell 里跑要么被墙要么污染他的进程。
- 用 `Read` / `Grep` / `Glob` 就能核对他改得对不对，不要主动写文件。
- **绝不 `git add` / `git commit` / `git push`**——除非用户明确要求。他自己会提交。

---

## 10. 会话历史速查（非必需，方便你追溯来路）

- **上上轮会话（transcript `752945cb`，2026-07-03）**：解决 Lombok `JCTree$JCImport` 报错 → 版本升级 → 冒烟测试 → 注册功能实现
- **上轮会话（transcript `b974bc5e`，2026-07-06）**：用户提两个架构问题 → 你分析出"过度设计"结论 → 给出本轮作业单
- **本轮会话（当前）**：整理交接文档（就是这个文件）

`.jsonl` transcript 在 `C:\Users\ruanqinhui\.claude\projects\D--wechatProgram-user-center\`——需要溯源时可以读，但一般不用。

---

**接手须知就这些。有疑问直接问用户，别自己乱猜他偏好。**
