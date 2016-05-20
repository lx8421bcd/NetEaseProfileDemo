# NetEaseProfileDemo  
仿照网易云音乐歌手资料页面滑动效果实现的Demo.<br>
A Demo Modeled on sliding effect of NetEase Cloud Music singer profile page.
##Features
![image](https://github.com/ShonLin/NetEaseProfileDemo/blob/master/picture/demo.gif)

##Introduction
基于原项目代码抽出修改而成，使用了Activity中嵌套HomeFragment的布局，在列表页（HomeListFragment）和歌手资料页（HomeProfileFragment）监听其中滑动组件的滑动距离，滑动距离与Header变动距离，颜色变化是一次函数关系，因此在初始化时先根据设定值计算好k值，在HomeFragment中调整对应Header，实现PagerSlidingTabStrip的悬停，顶部NavigationBar变色等效果。<br><br>
在不同页面切换时，为了防止各个页面的滑动距离不同使列表与Header显示异常，因此在切换到某一页面时需要重设其滑动距离为当前Header所滑动的距离。<br><br>
这里没有采用网易云音乐的下拉刷新方法，而是使用了下拉刷新布局，为了防止下拉刷新与页面上滑操作冲突，在下拉刷新布局中需要重设对滑动事件的监听，在列表没有滑到顶端时不触发下拉刷新。

-----
This demo based on one of my project, used fragment to show views and used activity to manage fragment.  I set Listeners on the list page and profile page to listen the scroll distance, the relationship between scrolling distance and header view changes can be described by a linear function, so we calculated the k value at initialization, change header according to the scrolling distance to achieve the PagerSlidingTabStrip hover effect and Toolbar color changes.<br><br>
To avoid the exceptions at Header display that caused by the different scrolling distance in each page when page swithing, we need to reset the current page scrolling distance to current header scrolling distance.<br><br>
In this demo, I did not use the pull-to-refresh method that NetEase Cloud Music has used, I used SwipRefreshLayout instead, in order to avoid the conflict between swip refresh and scroll up, we need to override onInterceptTouchEvent() at SwipRefreshLayout to judge tigger refresh or not.

## License
    Copyright (c) 2016 linxiao

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


