import styles from './TrainingList.module.css'

import { useState, useEffect } from 'react';
import axios from "axios";
import config from '../config';

import { BodyPart, changeJp } from '../constant/BodyPart'

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
    }, [popUp])

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
            <div className={styles.buttonArea}>
                <div className={styles.button} value='all' onClick={() => setBodyPart('all')}>ALL</div>
                <div className={styles.button} value={BodyPart.CHEST} onClick={() => setBodyPart(BodyPart.CHEST)}>{changeJp(BodyPart.CHEST)}</div>
                <div className={styles.button} value={BodyPart.SPINE} onClick={() => setBodyPart(BodyPart.SPINE)}>{changeJp(BodyPart.SPINE)}</div>
                <div className={styles.button} value={BodyPart.LEG} onClick={() => setBodyPart(BodyPart.LEG)}>{changeJp(BodyPart.LEG)}</div>
                <div className={styles.button} value={BodyPart.SHOULDER} onClick={() => setBodyPart(BodyPart.SHOULDER)}>{changeJp(BodyPart.SHOULDER)}</div>
                <div className={styles.button} value={BodyPart.ARM} onClick={() => setBodyPart(BodyPart.ARM)}>{changeJp(BodyPart.ARM)}</div>
                <div className={styles.button} value={BodyPart.ABS} onClick={() => setBodyPart(BodyPart.ABS)}>{changeJp(BodyPart.ABS)}</div>
            </div>
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
            <table className={styles.table}>
                <thead>
                    <tr>
                        <th className={styles.tName}>トレーニング名</th>
                        <th className={styles.tMaxWeight}>Max(kg)</th>
                        <th className={styles.tLastDay}>更新日時</th>
                        <th className={styles.tBodyPart}>部位</th>
                        <th className={styles.tRecord}></th>
                        <th className={styles.tDelete}></th>
                        <th className={styles.tWorkout}></th>
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
                <td className={styles.tName}>{data.name}</td>
                <td className={styles.tMaxWeight}>{data.maxWeight}</td>
                <td className={styles.tLastDay}>{data.lastDay}</td>
                <td className={styles.tBodyPart}>{changeJp(data.bodyPart)}</td>
                <td className={styles.tRecord}><div onClick={
                    () => {
                        setTableName('record')
                        setTargetData({ id: data.id, name: data.name, maxweight: data.maxweight })
                    }
                }>履歴</div></td>
                <td className={styles.tDelete}><div onClick={
                    () => {
                        setPopUp('delete_training')
                        setTargetData({ id: data.id, name: data.name, maxweight: data.maxweight })
                    }
                }>削除</div></td>
                <td className={styles.tWorkout}><div onClick={
                    () => {
                        setPopUp('workout')
                        setTargetData({ id: data.id, name: data.name, maxweight: data.maxweight })
                    }
                }>Workout!</div></td>
            </tr>
        </>
    )
}

function AddTrainingButton({ setPopUp }) {

    return (
        <>
            <div className={styles.addTrainingButton} onClick={() => setPopUp('training')}>トレーニング新規登録</div>
        </>
    )
}