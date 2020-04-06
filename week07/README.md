1. 由于未知错误,pom.xml中配置的依赖为本地包,有可能需要修改路径才能使用.
2. 由于同样的错误,使用本地javafx11+idea打开的话,
有可能要在`File | Settings | Appearance & Behavior | Path Variables`内设置环境变量,
再在MainApp的VM options中添加`--module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml`
来解决
3. 只实现了基本功能,pause按钮没有对应事件,只有其余的按钮有用.