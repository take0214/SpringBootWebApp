import React, { useContext, useState } from 'react';

import TrainningList from "../TrainningList/TrainningList";
import RecordList from "../RecordList/RecordList";
import PopUp from '../PopUp/PopUp';

export default function Table() {
    const [tableName, setTableName] = useState('training')
    const [targetData, setTargetData] = useState({ id: 0, name: null, maxweight: 0 })
    const [targetRecord, setTargetRecord] = useState({ id: 0, date: null })
    const [popUp, setPopUp] = useState('off')

    console.log(tableName)
    console.dir(targetData)

    if (tableName === 'training') {
        return (
            <>
                <TrainningList setTableName={setTableName} setTargetData={setTargetData} popUp={popUp} setPopUp={setPopUp} />
                <PopUp targetData={targetData} targetRecord={targetRecord} popUp={popUp} setPopUp={setPopUp} />
            </>
        )
    } else if (tableName === 'record') {
        return (
            <>
                <RecordList targetData={targetData} setTableName={setTableName} setTargetRecord={setTargetRecord} popUp={popUp} setPopUp={setPopUp} />
                <PopUp targetData={targetData} targetRecord={targetRecord} popUp={popUp} setPopUp={setPopUp} />
            </>
        )
    } else if (tableName === 'dailyRecord') {
        return (
            <>
            </>
        )
    }

}