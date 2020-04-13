1. 由于未知错误,pom.xml中配置的依赖不得不配置为本地包,所以需要修改pom.xml中的`sdk_lib`为本地路径.
EX:`C:/Program Files/Java/javafx-sdk-11.0.2/lib`
2. 由于同样的错误,使用本地javafx11+idea打开的话,
有可能要在`File | Settings | Appearance & Behavior | Path Variables`内设置环境变量(本地JAVAFX的路径),
(ex:`PATH_TO_FX`:`C:/Program Files/Java/javafx-sdk-11.0.2/lib`)
再在MainApp的VM options中添加`--module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml`
来解决
3. 只实现了基本功能,pause按钮没有对应事件,只有其余的按钮有用.