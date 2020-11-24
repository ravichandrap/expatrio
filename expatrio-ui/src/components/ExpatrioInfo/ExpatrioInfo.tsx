import React, {FC} from 'react';
import {ExpatrioWrapper} from "./ExpatrioWrapper";
import {ExpatrioHeader} from "../header/ExpatrioHeader";
import {Dashboard} from "../dashboard/Dashboard";

export const ExpatrioInfo: FC = () => {

    return <ExpatrioWrapper>
        <ExpatrioHeader/>
        <Dashboard/>
    </ExpatrioWrapper>
};