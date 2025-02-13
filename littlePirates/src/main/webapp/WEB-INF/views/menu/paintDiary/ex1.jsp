<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
		:root {
		    --bg-color: #fff;
		    --bg-transparent: rgba(255, 255, 255, 0);
		    --border-color: #d12e2e;
		}
		
		.manuscript-container {
		    margin: 0 auto;
		}
		
		.manuscript {
		    font-size: 16px;
		    line-height: 32px;
		    padding: 8px 0;
		    border: 2px solid var(--border-color);
		    background: -webkit-linear-gradient(
		            top,
		            var(--bg-transparent) 0%,
		            var(--bg-transparent) 1px,
		            var(--bg-color) 1px,
		            var(--bg-color) 8px,
		            var(--bg-transparent) 8px,
		            var(--bg-transparent) 100%
		        ),
		        -webkit-linear-gradient(
		            top,
		            var(--bg-transparent) 0%,
		            var(--bg-transparent) 1px,
		            var(--bg-color) 1px,
		            var(--bg-color) 8px,
		            var(--bg-transparent) 8px,
		            var(--bg-transparent) 100%
		        ),
		        -webkit-linear-gradient(
		            left,
		            var(--border-color) 0%,
		            var(--border-color) 1px,
		            var(--bg-transparent) 1px,
		            var(--bg-transparent) 100%
		        ),
		        -webkit-linear-gradient(
		            top,
		            var(--border-color) 0%,
		            var(--border-color) 1px,
		            var(--bg-transparent) 1px,
		            var(--bg-transparent) 100%
		        ),
		        -webkit-linear-gradient(
		            top,
		            var(--border-color) 0%,
		            var(--border-color) 1px,
		            var(--bg-transparent) 1px,
		            var(--bg-transparent) 100%
		        ),
		        var(--bg-color);
		    background: linear-gradient(
		            to bottom,
		            var(--bg-transparent) 0%,
		            var(--bg-transparent) 1px,
		            var(--bg-color) 1px,
		            var(--bg-color) 8px,
		            var(--bg-transparent) 8px,
		            var(--bg-transparent) 100%
		        ),
		        linear-gradient(
		            to bottom,
		            var(--bg-transparent) 0%,
		            var(--bg-transparent) 1px,
		            var(--bg-color) 1px,
		            var(--bg-color) 8px,
		            var(--bg-transparent) 8px,
		            var(--bg-transparent) 100%
		        ),
		        linear-gradient(
		            to right,
		            var(--border-color) 0%,
		            var(--border-color) 1px,
		            var(--bg-transparent) 1px,
		            var(--bg-transparent) 100%
		        ),
		        linear-gradient(
		            to bottom,
		            var(--border-color) 0%,
		            var(--border-color) 1px,
		            var(--bg-transparent) 1px,
		            var(--bg-transparent) 100%
		        ),
		        linear-gradient(
		            to bottom,
		            var(--border-color) 0%,
		            var(--border-color) 1px,
		            var(--bg-transparent) 1px,
		            var(--bg-transparent) 100%
		        ),
		        var(--bg-color);
		    background-size: 24px 32px;
		    margin: 0 auto;
		    background-position: 0 -1px, 0 0, -1px 0, 0 8px, 0px -1px;
		}
		
		.manuscript > p {
		    display: flex;
		    flex-wrap: wrap;
		    margin: -8px -1px 8px 0;
		    font-size: 16px;
		    line-height: 24px;
		}
		
		.manuscript > p > span {
		    display: inline-block;
		    text-align: center;
		    width: 24px;
		    height: 24px;
		    flex-shrink: 0;
		    margin-top: 8px;
		}
		
		.manuscript > :empty:not(img) {
		    height: 32px;
		    margin-bottom: 8px;
		}
		
		.manuscript > :last-child {
		    margin-bottom: 0;
		}
		
		.manuscript > img {
		    display: block;
		    width: auto;
		    max-width: 100%;
		    margin: 0 auto;
		    object-fit: cover;
		}
		
		.manuscript > *:not(:last-child) {
		    margin-bottom: 40px;
		}
		</style>
		<script>
		function initManuscript() {
		    const manuscript = document.querySelectorAll(".manuscript");
		    const handleResize = () => {
		        manuscript.forEach((elt) => {
		            resizeMnuascriptContainer(elt);
		            resizeImage(elt);
		        });
		    };

		    window.addEventListener("load", handleResize, { passive: true });
		    window.addEventListener("resize", handleResize, { passive: true });

		    manuscript.forEach((element) => {
		        element.querySelectorAll("p").forEach((element) => {
		            const text = element.innerText;

		            element.innerHTML = "";
		            [...text].forEach((word) => {
		                const span = document.createElement("span");
		                const textNode = document.createTextNode(word);

		                span.appendChild(textNode);
		                element.append(span);
		            });
		        });
		    });

		    handleResize();
		}

		function resizeMnuascriptContainer(element) {
		    element.style.width = `${
		        (Math.floor(element.parentElement.offsetWidth / 24) - 1) * 24 - 1
		    }px`;
		}

		function resizeImage(element) {
		    element.querySelectorAll("img").forEach((img) => {
		        const { naturalWidth, naturalHeight } = img;
		        const ratio = naturalHeight / naturalWidth;
		        const newHeight = element.offsetWidth * ratio;

		        img.height = Math.floor(newHeight - (newHeight % 32) - 8);
		    });
		}

		initManuscript();
		</script>
	</head>
	<body>
		<div class="manuscript-container">
		    <div class="manuscript">
		        <p>
		            오늘도 또 우리 수탉이 막 쫓기었다. 내가 점심을 먹고 나무를 하러 갈
		            양으로 나올 때이었다. 산으로 올라서려니까 등뒤에서 푸르득푸드득,
		            하고 닭의 횃소리가 야단이다. 깜짝 놀라서 고개를 돌려보니
		            아니나다르랴, 두 놈이 또 얼리었다.
		        </p>
		        <p>
		            점순네 수탉(은 대강이가 크고 똑 오소리같이 실팍하게 생긴 놈)이
		            덩저리 작은 우리 수탉을 함부로 해내는 것이다. 그것도 그냥 해내는
		            것이 아니라 푸드득 하고 면두를 쪼고 물러섰다가 좀 사이를 두고 또
		            푸드득 하고 모가지를 쪼았다. 이렇게 멋을 부려 가며 여지없이닦아
		            놓는다. 그러면 이 못생긴 것은 쪼일 적마다 주둥이로 땅을 받으며 그
		            비명이 킥, 킥할 뿐이다. 물론 미처 아물지도 않은 면두를 또 쪼이어
		            붉은 선혈은 뚝뚝 떨어진다.
		        </p>
		        <img src="https://i.imgur.com/pEmnc36.jpg" alt="roster" />
		        <p>
		            이걸 가만히 내려다보자니 내 대강이가 터져서 피가 흐르는 것같이 두
		            눈에서 불이 번쩍난다. 대뜸 지게 막대기를 메고 달려들어 점순네 닭을
		            후려칠까 하다가 생각을 고쳐먹고헛매질로 떼어만 놓았다.
		        </p>
		        <p>
		            이번에도 점순이가 쌈을 붙여 놨을 것이다. 바짝바짝 내 기를 올리느라고
		            그랬음에 틀림없을 것이다.
		        </p>
		        <p>
		            고놈의 계집애가 요새로 들어서서 왜 나를 못 먹겠다고 고렇게
		            아르렁거리는지 모른다.
		        </p>
		        <p>나흘 전 감자 조각만 하더라도 나는 저에게 조금도 잘못한 것은 없다.</p>
		    </div>
		</div>
	</body>
</html>