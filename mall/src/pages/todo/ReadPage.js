import React from "react";
import { createSearchParams, useNavigate, useParams, useSearchParams } from "react-router-dom";

const ReadPage = (props) => {
    const {tno} = useParams();
    const navigate = useNavigate();
    const [queryParams] = useSearchParams();

    const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
    const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

    const queryStr = createSearchParams({page, size}).toString();
    console.log(queryStr)

    const moveToModify = (tno) => {
        navigate({
            pathname: `/todo/modify/${tno}`,
            search: queryStr
        });
    };

    const moveToList = () => {
        navigate({
            pathname: `/todo/list`,
            search: queryStr
        });
    }

    return (
        <div className="text-3xl">
            Todo Read Page {tno}
            <div>
                <button onClick={()=>moveToModify(tno)}>Test Modify</button>
                <button onClick={moveToList}>Test List</button>
            </div>
        </div>
    )
}
export default ReadPage;