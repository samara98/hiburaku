import { ExpressContext } from 'apollo-server-express';
import axios, { AxiosInstance } from 'axios';

const movieServer = axios.create({ baseURL: process.env.MOVIE_SERVER! });
const tvServer = axios.create({ baseURL: process.env.TV_SERVER! });

export interface ServerContext {
  movieServer: AxiosInstance;
  tvServer: AxiosInstance;
}

export type Context = ServerContext & ExpressContext;

const context = ({ req, res }: ExpressContext): Context => ({
  movieServer,
  tvServer,
  req,
  res,
});

export default context;
