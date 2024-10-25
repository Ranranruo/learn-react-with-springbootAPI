import React from "react";
import ListComponent from "../../components/todo/ListComponent";

const ListPage = (props) => {
    return (
        <div className="p-4 w-full bg-white">
            <div className="text-3xl font-extrabold">
                <ListComponent/>
            </div>
                Todo List Page Component
        </div>
    )
}
export default ListPage;