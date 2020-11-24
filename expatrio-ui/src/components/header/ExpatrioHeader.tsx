import React, {FC} from 'react';

export const ExpatrioHeader: FC = () => {
    return <div className="header">
        <a href="#default" className="logo">Expatrio</a>
        <div className="header-right">
            <a className="active" href="#home">Home</a>
            <a href="#contact">Create User</a>
            <a href="#contact">Users</a>
            <a href="#about">Login</a>
        </div>
    </div>
}
