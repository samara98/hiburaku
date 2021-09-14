import { gql } from 'apollo-server-express';

const typeDefs = gql`
  type Query {
    # movie
    getMovieList: [Entertainment!]!
    getMovie(movieId: Int!): Entertainment!

    # tv
    getTvList: [Entertainment!]!
    getTv(tvId: Int!): Entertainment!
  }

  type Mutation {
    # movie
    createMovie(data: InputEntertainment!): Entertainment!
    updateMovie(movieId: Int!, data: InputEntertainment!): Entertainment!
    deleteMovie(movieId: Int!): Entertainment

    # tv
    createTv(data: InputEntertainment!): Entertainment!
    updateTv(tvId: Int!, data: InputEntertainment!): Entertainment!
    deleteTv(tvId: Int!): Entertainment
  }

  type Entertainment {
    id: Int!
    title: String!
    overview: String!
    posterPath: String!
    popularity: Float!
  }

  input InputEntertainment {
    title: String!
    overview: String!
    posterPath: String!
    popularity: Float!
  }
`;

export default typeDefs;
