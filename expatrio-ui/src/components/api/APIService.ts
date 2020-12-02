import { getEnvironmentVariables } from "../utils/getEnvironmentVariables";
import { Dispatch } from "react";
import { UsersAction } from "../typings/UsersAction";
import { IS_LOADING, REQUEST_ERROR, SET_USERS, LOG_IN, SET_MESSAGE, UPDATE_USER } from "../utils/Constants";
import { User } from "../typings/User";
import axios, { AxiosResponse } from 'axios';

const { BASE_URL } = getEnvironmentVariables();

const getURL = (role: string) => `${BASE_URL}/role/${role}`;
const deleteURL = (id: string) => `${BASE_URL}/${id}`;
const postURL = () => BASE_URL
const loginURL = () => `${BASE_URL}/login`

const urlUtil = () => {
    return {
        LOGIN_URL: `${BASE_URL}/auth/loginuser`,
        ALL_USERS_URL: `http://localhost:8081/api/v1/user`,
        UPDATE_USER_URL: `http://localhost:8081/api/v1/user`,
    }
}

const getHeader = (authorization: string) => {
    return {
        headers: {
            Authorization: "Baere  " + authorization
        }
    }
}

export async function loginUser(email: string, password: string, dispatch: Dispatch<UsersAction>) {
    try {
        dispatch({ type: IS_LOADING });
        const { data }: AxiosResponse<UserResponse> = await axios.post(
                                        urlUtil().LOGIN_URL, 
                                        { email: email, password: password }
                                    );
        dispatch({
            type: LOG_IN,
            authorization: data.authorization,
            user: data.user
        })
    } catch (error) {
        console.log(error);
        dispatch({ type: REQUEST_ERROR, error: error });
    }
}

export async function fetchUsers(role: string = "Customer",
        dispatch: Dispatch<UsersAction>
    ) {
        try {
            dispatch({ type: IS_LOADING });
            const { data }: AxiosResponse<User[]> = await axios.get(getURL(role));
            //dispatch({ type: SET_USERS, users: data });
        } catch (error) {
            dispatch({ type: REQUEST_ERROR, error: error.message });
        }
}

export async function fetchAllUsers(authorization: string,
        dispatch: Dispatch<UsersAction>
    ) {
        try {
            dispatch({ type: IS_LOADING });
            const { data }: AxiosResponse<AllUserResponse> = 
                        await axios.get(
                            urlUtil().ALL_USERS_URL, 
                            getHeader(authorization)
                        );

            dispatch({ 
                type: SET_USERS, 
                users: data.users, 
                authorization: data.authorization 
            }); 

        } catch (error) {
            dispatch({ type: REQUEST_ERROR, error: error.message });
        }
}

export async function updateUser(
                        user: User, 
                        authorization: string,
                        dispatch: Dispatch<UsersAction>) {
                            console.log(user);
                            
    try {
        const {data}:AxiosResponse<UserResponse> = await axios.post(
                    urlUtil().UPDATE_USER_URL, 
                    user, 
                    getHeader(authorization));
            console.log(data);
            
        dispatch({ 
            type: UPDATE_USER,
            user: data.user,
            authorization: data.authorization,
            message: `User ${user.lastName+', '+ user.firstName} has been ${user.id ? 'updated': 'create'}` 
        })
    } catch (error) {
        console.log(error);
        dispatch({ type: REQUEST_ERROR, error: error.message });
    }
}

export async function deleteUser(id: string, dispatch: Dispatch<UsersAction>) {
    try {
        await axios.delete(deleteURL(id));
        dispatch({ type: SET_MESSAGE, message: "User has been deleted!" })
    } catch (error) {
        console.log(error);
        dispatch({ type: REQUEST_ERROR, error: error.message });
    }
}

interface Auth {
    authorization: string;
}
interface UserResponse extends Auth {
    user: User;
}

interface AllUserResponse extends Auth {
    users: User[];
}

