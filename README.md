![](https://dpnw.site/assets/img/logo_white.png)

![](https://dpnw.site/assets/img/desc_card/dppcore.jpg)

## All DP-Plugins depend on the [DPP-Core](https://dpnw.site/plugin.html?plugin=DPP-Core) plugin. <br>Please make sure to install [DPP-Core](https://dpnw.site/plugin.html?plugin=DPP-Core)

## Discord
### Join our Discord server to get support and stay updated with the latest news and updates.

### If you have any questions or suggestions, please join our Discord server.

### If you find any bugs, please report them using the inquiry channel.

<span style="font-size: 18px;">**Discord Invite: https://discord.gg/JnMCqkn2FX**</span>

<br>
<br>

<details>
	<summary>korean</summary>

![](https://dpnw.site/assets/img/desc_card/desc.jpg)

## DP-ConsumeBox 플러그인 소개
DP-ConsumeBox는 커스터마이징 가능한 보상 상자 플러그인입니다.<br>
서버 운영자가 원하는 대로 상자를 설정하고, 유저는 다양한 방식으로 보상을 획득할 수 있습니다.<br>
직관적인 GUI와 강력한 기능으로, 이벤트 보상부터 꾸미기 아이템 지급까지 쉽게 활용하세요!

## 플러그인 특징
- **GUI 편집 지원**: 쿠폰 아이템과 보상 아이템을 GUI로 수정할 수 있습니다.
- **페이지 기능 지원**: 보상 아이템 설정 시 여러 페이지에 설정할 수 있습니다.
- **보상 갯수 설정**: 랜덤/선택 상자에서 얻을 수 있는 보상의 갯수를 수정할 수 있습니다.
- **다양한 상자 시스템**: 아래와 같은 다양한 상자 시스템을 지원합니다.
 - **랜덤 상자**: 설정된 보상 아이템 중에서 랜덤으로 지급받습니다.
 - **선택 상자**: 설정된 보상 아이템 중에서 선택하여 지급받습니다.
 - **선물 상자**: 설정된 보상 아이템 전부를 지급받습니다.


## 의존성
- DPP-Core

<br>
<br>

![](https://dpnw.site/assets/img/desc_card/cmd-perm.jpg)

## 명령어
### 관리자 명령어
| 명령어                         | 설명                                                          |
|-----------------------------|-------------------------------------------------------------|
| `/dpcb create <name> <type>` | 보상 상자를 생성합니다. (type: gift/select/random)                    |
| `/dpcb delete <name>`       | 보상 상자를 삭제합니다.                                               |
| `/dpcb coupon <name>`       | 보상 상자의 쿠폰 아이템을 설정하는 GUI를 엽니다.                               |
| `/dpcb type <name> <type>`  | 보상 상자의 타입을 설정합니다. (type: gift/select/random)                |
| `/dpcb item <name>`         | 보상 상자의 보상 아이템을 설정하는 GUI를 엽니다.                               |
| `/dpcb give <name> (player)` | 플레이어에게 보상 상자를 지급합니다.                                        |
| `/dpcb list`                | 보상 상자 목록을 확인하는 GUI를 엽니다. (GUI에서 해당 보상 상자를 클릭하면 본인에게 지급됩니다.) |
| `/dpcb drop <name> <drop>`  | 보상 상자의 보상 갯수를 설정합니다. (기본 값 : 1, 보상 아이템이 설정되어 있어야 함.)        |
| `/dpcb page <name> <page>`  | 보상 상자의 최대 페이지를 설정합니다. (기본 값 : 0)                            |
**주의사항**: 페이지는 0부터 시작입니다. 본인이 설정하고 싶은 최대 페이지에 -1을 한 값을 사용하십시오. 
 ex) 설정하고 싶은 최대 페이지 : 2 => 명령어 사용 : `/dpcb page test 1`

### 권한
- `dpcb.create`: 보상 상자 생성 권한
- `dpcb.delete`: 보상 상자 제거 권한
- `dpcb.coupon`: 보상 상자 쿠폰 아이템 설정 권한
- `dpcb.type`: 보상 상자 타입 설정 권한
- `dpcb.item`: 보상 상자 보상 아이템 설정 권한.
- `dpcb.give`: 보상 상자 지급 권한.
- `dpcb.list`: 보상 상자 목록 확인 권한.
- `dpcb.drop`: 보상 상자 보상 갯수 설정 권한.
- `dpcb.page`: 보상 상자 최대 페이지 설정 권한.

## 사용법 예시
- 보상 상자 생성: `/dpcb create test random`
- 보상 상자 쿠폰 아이템 설정: `/dpcb coupon test`
- 보상 상자 타입 설정: `/dpcb type test select`
- 보상 상자 아이템 설정: `/dpcb item test`
- 보상 상자 보상 갯수 설정: `/dpcb drop test 2`
- 보상 상자 최대 페이지 설정: `/dpcb page test 1`
- 보상 상자 목록 확인: `/dpcb list`
- 보상 상자 지급: `/dpcb give test (playerName)`


</details>

<details open>
	<summary>english</summary>

![](https://dpnw.site/assets/img/desc_card/desc.jpg)
## DP-ConsumeBox Plugin Introduction
DP-ConsumeBox is a customizable reward box plugin.<br>
Server administrators can configure boxes as desired, and players can obtain rewards in various ways.<br>
With an intuitive GUI and powerful features<br>
it can be easily used for everything from event rewards to cosmetic item distribution!

## Plugin Features
- **GUI Editing Support**: Coupon items and reward items can be modified via a GUI.
- **Page Functionality**: Reward items can be set across multiple pages.
- **Reward Quantity Settings**: The number of rewards obtainable from random/select boxes can be customized.
- **Various Box Systems**: Supports the following box systems:
 - **Random Box**: Rewards are randomly granted from the set reward items.
 - **Select Box**: Players can choose a reward from the set reward items.
 - **Gift Box**: All set reward items are granted.

## Dependencies
- DPP-Core

<br>
<br>

## Commands
### Admin Commands
| Command                        | Description                                                                 |
|--------------------------------|-----------------------------------------------------------------------------|
| `/dpcb create <name> <type>`   | Creates a reward box. (type: gift/select/random)                            |
| `/dpcb delete <name>`          | Deletes a reward box.                                                      |
| `/dpcb coupon <name>`          | Opens a GUI to set the coupon item for a reward box.                        |
| `/dpcb type <name> <type>`     | Sets the type of a reward box. (type: gift/select/random)                   |
| `/dpcb item <name>`            | Opens a GUI to set the reward items for a reward box.                       |
| `/dpcb give <name> (player)`   | Grants a reward box to a player.                                            |
| `/dpcb list`                   | Opens a GUI to view the list of reward boxes. (Clicking a box in the GUI grants it to the player.) |
| `/dpcb drop <name> <drop>`     | Sets the number of rewards for a reward box. (Default: 1, requires reward items to be set.) |
| `/dpcb page <name> <page>`     | Sets the maximum page for a reward box. (Default: 0)                        |
**Note**: Pages start from 0. Use the desired maximum page number minus 1.  
Example: Desired maximum page = 2 → Command: `/dpcb page test 1`

### Permissions
- `dpcb.create`: Permission to create a reward box.
- `dpcb.delete`: Permission to delete a reward box.
- `dpcb.coupon`: Permission to set a reward box's coupon item.
- `dpcb.type`: Permission to set a reward box's type.
- `dpcb.item`: Permission to set a reward box's reward items.
- `dpcb.give`: Permission to grant a reward box.
- `dpcb.list`: Permission to view the reward box list.
- `dpcb.drop`: Permission to set the number of rewards for a reward box.
- `dpcb.page`: Permission to set the maximum page for a reward box.

## Usage Examples
- Create a reward box: `/dpcb create test random`
- Set a reward box coupon item: `/dpcb coupon test`
- Set a reward box type: `/dpcb type test select`
- Set a reward box's reward items: `/dpcb item test`
- Set the number of rewards for a reward box: `/dpcb drop test 2`
- Set the maximum page for a reward box: `/dpcb page test 1`
- View the reward box list: `/dpcb list`
- Grant a reward box: `/dpcb give test (playerName)`
</details>

<br>
<br>
