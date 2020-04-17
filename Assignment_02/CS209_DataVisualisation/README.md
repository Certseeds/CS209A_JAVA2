1. default load ./config.yml
2. default file quantity statistics is sorted.
3. in the second part, ignore the ".*" files(mainly for ignore ".DS_Store")
4. add some test yml file in root path.
    + config_empty.yml is empty, set intervals for it will get a alert.
    + config_no_1.yml lack of IntervalNames, set interval use it will get a alert.
    + config_no_2.yml lack of Interval, set interval use it will get a alert,too.
    + config_not_match.yml's IntervalNames and Interval items number do not match,set interval use it will get a alert.
5. change both two settings use the yml's will also display those information.
    + if use set config to load a wrong yml file which's root path is not correct,then it will display a alert.
6. use the button, can change the file size statistics to piechart,barchart form 1 and barchart form 2.
7. 由于未知错误,pom.xml中配置的依赖不得不配置为本地包,所以需要修改pom.xml中的`sdk_lib`为本地路径.
EX:`C:/Program Files/Java/javafx-sdk-11.0.2/lib`
8. 由于同样的错误,使用本地javafx11+idea打开的话,
有可能要在`File | Settings | Appearance & Behavior | Path Variables`内设置环境变量(本地JAVAFX的路径),
(ex:`PATH_TO_FX`:`C:/Program Files/Java/javafx-sdk-11.0.2/lib`)
再在Main的VM options中添加`--module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml`
来解决