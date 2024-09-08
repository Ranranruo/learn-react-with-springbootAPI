import { createBrowserRouter } from "react-router-dom";
import { lazy, Suspense } from "react";
import todoRouter from "./todoRouter";

// 코드 스플리팅

// 페이지 불러오기전 띄워줄 로딩 화면
const Loading = <div className="bg-red-700">Loading...</div>;

// Main이 불러졌을때 데이터를 import 해올 수 있게 해주는 함수
const Main = lazy(()=> import("../pages/MainPage"));
const About = lazy(()=> import("../pages/AboutPage"));
const TodoIndex = lazy(()=> import("../pages/todo/IndexPage"));


// Router 셋팅
const root = createBrowserRouter([
    {
        path: '',
        // 인덱스 페이지 접근시 Main 페이지를 import해서 보여주고 데이터를 불러오기 전까지 Loading 표시
        element: <Suspense fallback={Loading}><Main /></Suspense>
    },
    {
        path: "about",
        element: <Suspense fallback={Loading}><About /></Suspense>
    },
    {
        path: "todo",
        element: <Suspense fallback={Loading}><TodoIndex /></Suspense>,
        children: todoRouter()
    }
]);

export default root;