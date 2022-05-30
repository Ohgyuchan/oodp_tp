## JSON CRUD
```java
// JSON 으로 파싱할 문자열
String str = "{\"name\" : \"apple\", \"id\" : 1, \"price\" : 1000}";
// JSONParser로 JSONObject로 변환
JSONObject jsonObject = (JSONObject) new JSONParser().parse(str);
// 추가
jsonObject.put("count", 5);
// 변경
jsonObject.put("id", 2);
jsonObject.replace("name", "banana");
// 삭제
jsonObject.remove("price");

```
> 출처: https://hianna.tistory.com/627 [어제 오늘 내일]
## Used Patterns


## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
