import { useState, useEffect } from 'react';
import axios from "axios";
import config from '../config';

import {BodyPart, changeJp} from '../constant/BodyPart'

export default function TrainningList({ setTableName, setTargetData, popUp, setPopUp }) {
    const [bodyPart, setBodyPart] = useState('all')
    const [data, setData] = useState([]);

    function getTraining() {
        return axios.get(`${config.API_URL}/training`).then((response) => {
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
            <Button setBodyPart={setBodyPart} />
            <List arrayData={data} bodyPart={bodyPart} setTableName={setTableName} setTargetData={setTargetData} setPopUp={setPopUp} />
            <AddTrainingButton setPopUp={setPopUp} />
        </>
    )
}

function Button({ setBodyPart }) {

    return (
        <>
            <button value='all' onClick={(e) => setBodyPart(e.target.value)}>ALL</button>
            <button value={BodyPart.CHEST} onClick={(e) => setBodyPart(e.target.value)}>{changeJp(BodyPart.CHEST)}</button>
            <button value={BodyPart.SPINE} onClick={(e) => setBodyPart(e.target.value)}>{changeJp(BodyPart.SPINE)}</button>
            <button value={BodyPart.LEG} onClick={(e) => setBodyPart(e.target.value)}>{changeJp(BodyPart.LEG)}</button>
            <button value={BodyPart.SHOULDER} onClick={(e) => setBodyPart(e.target.value)}>{changeJp(BodyPart.SHOULDER)}</button>
            <button value={BodyPart.ARM} onClick={(e) => setBodyPart(e.target.value)}>{changeJp(BodyPart.ARM)}</button>
            <button value={BodyPart.ABS} onClick={(e) => setBodyPart(e.target.value)}>{changeJp(BodyPart.ABS)}</button>
        </>
    )
}

function List({ arrayData, bodyPart, setTableName, setTargetData, setPopUp }) {
    const rows = [];

    for (const data of arrayData) {
        if (bodyPart === 'all') {
            rows.push(<ListRows data={data} setTableName={setTableName} setTargetData={setTargetData} setPopUp={setPopUp} />)
        } else {
            if (data.bodyPart === bodyPart)
                rows.push(<ListRows data={data} setTableName={setTableName} setTargetData={setTargetData} setPopUp={setPopUp} />)
        }
    }

    return (
        <>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>MaxWeight</th>
                        <th>LastDay</th>
                        <th>BodyPart</th>
                    </tr>
                </thead>
                <tbody>
                    {rows}
                </tbody>
            </table>
        </>
    )
}

function ListRows({ data, setTableName, setTargetData, setPopUp }) {

    return (
        <>
            <tr>
                <td>{data.name}</td>
                <td>{data.maxWeight}</td>
                <td>{data.lastDay}</td>
                <td>{changeJp(data.bodyPart)}</td>
                <td><div onClick={
                    () => {
                        setTableName('record')
                        setTargetData({ id: data.id, name: data.name, maxweight: data.maxweight })
                    }
                }>履歴</div></td>
                <td><div onClick={
                    () => {
                        setPopUp('workout')
                        setTargetData({ id: data.id, name: data.name, maxweight: data.maxweight })
                    }
                }>ワークアウト</div></td>
                <td><div onClick={
                    () => {
                        setPopUp('delete_training')
                        setTargetData({ id: data.id, name: data.name, maxweight: data.maxweight })
                    }
                }>削除</div></td>
            </tr>
        </>
    )
}

function AddTrainingButton({ setPopUp }) {

    return (
        <>
            <button onClick={() => setPopUp('training')}>新規登録</button>
        </>
    )
}


const TRAINING = [
    { id: 1, name: "tra1", maxweight: 10, lastday: "2024/01/01", bodypart: "胸" },
    { id: 2, name: "tra2", maxweight: 20, lastday: "2024/01/02", bodypart: "胸" },
    { id: 3, name: "tra3", maxweight: 30, lastday: "2024/01/03", bodypart: "背中" },
    { id: 4, name: "tra4", maxweight: 40, lastday: "2024/01/04", bodypart: "背中" },
    { id: 5, name: "tra5", maxweight: 50, lastday: "2024/01/05", bodypart: "背中" },
    { id: 6, name: "tra6", maxweight: 60, lastday: "2024/01/06", bodypart: "脚" },
    { id: 7, name: "tra7", maxweight: 70, lastday: "2024/01/07", bodypart: "脚" },
    { id: 8, name: "tra8", maxweight: 80, lastday: "2024/01/08", bodypart: "脚" }
];