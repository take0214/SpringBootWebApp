import { useState } from 'react';

import { Header } from "./Header/Header"
import PopUp from "./PopUp/PopUp"
import Table from "./Table/Table"

export default function Toppage() {

    return (
        <>
            <Header />
            <Table/>
        </>
    )
}

