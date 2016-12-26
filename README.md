# Appium-Opencv

## 环境
### 下载代码
git clone xxxxx
### 检查是否在master分支
git branch
### 如果不是，请切换到master
git checkout branch1
### 在intellijIdea中打开项目，然后把OpenCV添加进项目，前提是OpenCV已按照文档提前装好
project structure  -->  Modules  -->  1_test  --> click "+" at the bottom  --> JARs or directories...
-->  add /your/path/opencv-310.jar (/opt/local/share/OpenCV/java/opencv-310.jar) 
### run的时候，需要配置VMoptions
Edit Configurations... -->  Configuration  -->  add -Djava.library.path=/your/path/OpenCV/java/ (-Djava.library.path=/opt/local/share/OpenCV/java/) to VM options

## task
### 模版匹配
refer http://docs.opencv.org/2.4/doc/tutorials/imgproc/histograms/template_matching/template_matching.html#template-matching

#### 识别lena的脸
1.完成matchAndFindLoc函数，获取最佳匹配位置
2.完成getMatchArea函数，画出匹配区域

### 练习与Appium结合，识别login，实现微信的登录

### 颜色识别和轮廓勾勒
refer http://docs.opencv.org/2.4/doc/tutorials/imgproc/shapedescriptors/bounding_rects_circles/bounding_rects_circles.html#bounding-rects-circles





