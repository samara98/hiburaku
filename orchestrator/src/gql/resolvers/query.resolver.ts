import { ApolloError } from 'apollo-server-errors';
import axios from 'axios';
import { Context } from '../context';
import { Entertainment } from '../interfaces';

const Query = {
  // Movie
  getMovieList: async (_, __, { movieServer }: Context) => {
    try {
      const movieList = await movieServer.get<Entertainment[]>('/movies');
      return movieList.data;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },
  getMovie: async (_, { movieId }: { movieId: number }, { movieServer }: Context) => {
    try {
      const movieList = await movieServer.get<Entertainment[]>(`/movies/${movieId}`);
      return movieList.data;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },

  // TV
  getTvList: async (_, __, { tvServer }: Context) => {
    try {
      const movieList = await tvServer.get<Entertainment[]>('/tv');
      return movieList.data;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },
  getTv: async (_, { tvId }: { tvId: number }, { tvServer }: Context) => {
    try {
      const movieList = await tvServer.get<Entertainment[]>(`/tv/${tvId}`);
      return movieList.data;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },
};

export default Query;
