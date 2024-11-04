import { useEffect, useState } from "react";
import { getOne } from "../../apis/todoAPI";

const initState = {
    tno: 0,
    title: '',
    writer: '',
    dueDate: '',
    complete: false
}

const ModifyComponent = ({tno}) => {
    const [todo, setTodo] = useState(initState);

    useEffect(()=>{

        getOne(tno).then(data=>{
            setTodo(data);
        })
    }, [tno])

    return(
        <div className="border-2 border-sky-200">
            
        </div>
    )
}

export default ModifyComponent;