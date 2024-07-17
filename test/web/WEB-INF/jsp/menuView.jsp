<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 7:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>主页菜单</title>


    <script type="text/javascript">
        function exit() {
            return window.confirm("您,确定要退出满汉楼吗 ?")
        }
    </script>
</head>

<style tyle="text/css">

    /* 页面的初始化加载 */
    * {
        /* 一个数值外补丁,上下左右四个位置的值 */
        margin: 0;
        /* 一个数值内补丁，上下左右四个位置的值 */
        padding: 0;
        /* 标准盒子样式 */
        box-sizing: border-box;
        /* font-family: "Poppins",sans-serif; */
    }


    /* 设置三个枫叶整体布局位置的样式 */
    section {
        /* 相对定位 */
        position: relative;
        width: 100%;
        height: 100vh;
        /* 设置渐变背景 */
        background: radial-gradient(#333, #000);
        /*隐藏溢出的画面,hidden不显示超过对象尺寸的内容*/
        overflow: hidden;
        /* 弹性布局，横向排列 */
        display: flex;
        /* 水平居中 */
        justify-content: center;
        /* 上下垂直居中 */
        align-content: center;

        /* vh是CSS3中的相对长度单位，表示相对视口高度（Viewport Height），视口被均分为100单位的vh，即1vh = 1% * 视口高度。可以用来解决主体内容不足以撑起视口的剩余高度时，页面底部留白太多的尴尬问题。
        vh是viewpoint的缩写，是根据视口的大小而改变的相对单位
        1vh是占视口高度的百分之一
        1wh是占视口宽度的百分之一
        50wh就是盒子宽度占视口宽度的百分之五十 */
    }

    /* 字体样式 */
    section .a {
        font-size: 3em;
        /* 文字居中,上下左右:外补丁自适应 */
        margin: auto;
        /* 设置每个中文字,英文单词间的距离 */
        word-spacing: 30px;
        /* 设置每个文字,每个字母之间的距离 */
        letter-spacing:10px;
    }

    a{
        color:#fff;
        text-decoration:none
    }

    a:hover {
        color: aqua;
    }

    /* 设置每个枫叶div盒子的基本格式 */
    section .set {
        /* 绝对定位 */
        position: absolute;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
        /* 不会被鼠标点击影响到,没有也没有太大的影响 */
        pointer-events: none;

    }

    /* 单独设置第二个枫叶div盒子样式 */
    .set2 {
        transform: scale(2) rotateY(180deg);
        /* 透明过滤器 */
        filter: blur(2px);
        /* transform: 2D样式:scale()： 指定对象的2D scale（2D缩放）。第一个参数对应X轴，第二个参数对应Y轴。如果第二个参数未提供，则默认取第一个参数的值 rotate()： 指定对象的2D rotation（2D旋转），需先有 <' transform-origin '> 属性的定义  */
    }

    /* 单独对第二个枫叶div盒子设置样式 */
    .set3 {
        transform: scale(0.8) rotateX(180deg);
        filter: blur(4px);
    }

    /* 对枫叶图片外部的边框div盒子设置样式 */
    section .set div {
        /* 绝对定位 */
        position: absolute;
        /*弹性布局以块的形式布局,并且是每个块是独占行的*/
        display: block;
    }

    /* 对每个单独的枫叶盒子做动画处理 ,子节点*/
    /* nth-child伪类选择符: (匹配父元素的第n个子元素E，假设该子元素不是E，则选择符无效。 */
    section .set div:nth-child(1) {
        left: 20%;
        /* animation:复合属性。检索或设置对象所应用的动画特效。 */
        animation: animate 15s linear infinite;
        /* animation-delay 检索或设置对象动画延迟的时间 */
        animation-delay: -7s;
    }

    section .set div:nth-child(2) {
        left: 50%;
        animation: animate 20s linear infinite;
        animation-delay: -5s;
    }


    section .set div:nth-child(3) {
        left: 70%;
        animation: animate 20s linear infinite;
        animation-delay: 0s;
    }

    section .set div:nth-child(4) {
        left: 0%;
        animation: animate 15s linear infinite;
        animation-delay: -5s;
    }


    section .set div:nth-child(5) {
        left: 85%;
        animation: animate 18s linear infinite;
        animation-delay: -10s;
    }

    section .set div:nth-child(6) {
        left: 0%;
        animation: animate 12s linear infinite;
        animation-delay: 0s;
    }

    section .set div:nth-child(7) {
        left: 15%;
        animation: animate 14s linear infinite;
        animation-delay: 0s;
    }

    section .set div:nth-child(8) {
        left: 90%;
        animation: animate 18s linear infinite;
        animation-delay: -1s;
    }


    /* 处理伪类 定义动画 */
    @keyframes animate {
        0% {
            opacity: 0;
            top: -10%;
            transform: translateX(20px) rotate(0deg);
        }

        10% {
            opacity: 1;
        }

        20% {
            transform: translateX(-20px) rotate(45deg);
        }

        40% {
            transform: translateX(-20px) rotate(90deg);
        }

        60% {
            transform: translateX(20px) rotate(180deg);
        }

        80% {
            transform: translateX(-20px) rotate(180deg);
        }

        100% {
            top: 110%;
            transform: translateX(-20px) rotate(226deg);
        }


        /* transform: 2D样式:scale()： 指定对象的2D scale（2D缩放）。第一个参数对应X轴，第二个参数对应Y轴。如果第二个参数未提供，则默认取第一个参数的值 rotate()： 指定对象的2D rotation（2D旋转），需先有 <' transform-origin '> 属性的定义
        deg是CSS中的一个角度单位，表示度(Degress)，一个圆共360度,在CSS中角度单位有：度(deg)、梯度(grad)、弧度(rad)。无论如何声明，这些值都会解释为0~360范围内的度数*/
    }
</style>

<body>
<!-- 页眉 -->
<section>
    <div class="a">
        <div><a href="show">显示所有餐桌的状态</a></div>
        <div><a href="skipReserve">预定餐桌</a></div>
        <div><a href="allShowMenu">显示所有菜品</a></div>
        <div><a href="orderFood">点餐服务</a></div>
        <div><a href="showAllBillServlet">显示所有的账单信息</a></div>
        <div><a href="skipCheckOut1Servlet">结账</a></div>
        <div><a href="skipCancelReserve">取消预定</a></div>
        <div><a href="exitMhlServlet" onclick="return exit()" >退出满汉楼</a></div>
    </div>
    <!-- 先进行一个整体的布局,三个div盒子,每个div盒子放8个枫叶-->
    <div class="set set1">
        <div><img src="leaves1.png" alt=""></div>
        <div><img src="leaves2.png" alt=""></div>
        <div><img src="leaves3.png" alt=""></div>
        <div><img src="leaves4.png" alt=""></div>
    </div>
    <div class="set set2">
        <div><img src="leaves1.png" alt=""></div>
        <div><img src="leaves2.png" alt=""></div>
        <div><img src="leaves3.png" alt=""></div>
        <div><img src="leaves4.png" alt=""></div>
    </div>
    <div class="set set3">
        <div><img src="leaves1.png" alt=""></div>
        <div><img src="leaves2.png" alt=""></div>
        <div><img src="leaves3.png" alt=""></div>
        <div><img src="leaves4.png" alt=""></div>
    </div>
</section>
</body>
</html>
