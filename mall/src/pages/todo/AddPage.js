import { useState } from "react";
import AddComponent from "../../components/todo/AddComponent";



const AddPage = (props) => {

    return (
        <div className="p-4 w-full bg-white">
            <div className="text-3xl font-extrabold">
                Add Page
            </div>
            <AddComponent/>
        </div>
    )
}
export default AddPage;