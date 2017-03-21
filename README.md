# java-pdf
扫描文件夹中图片（包括子目录）生成pdf文件，使用文件夹名称和文件名称作为pdf的标签

# 问题
文件图片存在高度不同的情况。想的解决办法:
1、图片缩放到固定的高度和宽度，
但是这样图片会变形
2、每张图生成一页面，根据所有图片中最高的定义pdf的单页高度，如果图片中存在，一个图片长度很长，
会造成其他图片的当前页出现很高的空白页面
3、使用所有图片中最高的高度作为pdf的单页高度，然后每张图片，在根据自适应高度进行排版，
pdf单页有多个图片的效果
希望的效果，pdf每页，根据图片的的高度定义，而不是同一的一个高度
