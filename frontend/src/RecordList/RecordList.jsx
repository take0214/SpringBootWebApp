import { useState, useEffect } from "react";
import axios from "axios";

export default function RecordList({ targetData, setTableName, setTargetRecord, popUp, setPopUp }) {
    const [data, setData] = useState([]);

    function getTraining() {
        return axios.get(`http://localhost:8080/record?id=${targetData.id}`).then((response) => {
            console.dir(response.data)
            return response.data
        }).catch(error => {
            console.dir(error);
            return error
        });
    }

    useEffect(() => {
        getTraining().then(data => { setData(data) })
    },[popUp])

    return (
        <>
            <h2>{targetData.name}</h2>
            <List arrayData={data} setTargetRecord={setTargetRecord} setPopUp={setPopUp} />
            <BackButton setTableName={setTableName} />
        </>
    )
}

function BackButton({ setTableName }) {

    return (
        <>
            <button onClick={() => setTableName('training')}>戻る</button>
        </>
    )
}

function List({ arrayData, setTargetRecord, setPopUp }) {
    const rows = [];

    for(const record of arrayData){
        rows.push(<ListRows record={record} setTargetRecord={setTargetRecord} setPopUp={setPopUp} />)
    }

    return (
        <>
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Weight</th>
                        <th>Reps</th>
                    </tr>
                </thead>
                <tbody>
                    {rows}
                </tbody>
            </table>
        </>
    )
}

function ListRows({ record, setTargetRecord, setPopUp }) {
    return (
        <>
            <tr>
                <td>{record.date}</td>
                <td>{record.weight}</td>
                <td>{record.reps}</td>
                <td><div onClick={
                    () => {
                        setPopUp('delete_record')
                        setTargetRecord({ id: record.id, date: record.date })
                    }
                }>削除</div></td>
            </tr>
        </>
    )


}

const TRA1 = [
    { id: 1, date: "2024/01/01", weight: 10, reps: 1 },
    { id: 2, date: "2024/01/02", weight: 20, reps: 2 },
    { id: 3, date: "2024/01/03", weight: 30, reps: 3 },
    { id: 4, date: "2024/01/04", weight: 40, reps: 4 },
    { id: 5, date: "2024/01/05", weight: 50, reps: 5 },
    { id: 6, date: "2024/01/06", weight: 60, reps: 6 },
    { id: 7, date: "2024/01/07", weight: 70, reps: 7 },
    { id: 8, date: "2024/01/08", weight: 80, reps: 8 }
];








