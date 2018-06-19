/*
** stream_socket_client.c -- a small socket client program that let's you connect to eg. stream_socket_server
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <netdb.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define MAXDATASIZE 100

void *get_in_addr(struct sockaddr *sa) {
    if (sa->sa_family == AF_INET) {
        return &(((struct sockaddr_in *) sa)->sin_addr);
    }

    return &(((struct sockaddr_in6 *) sa)->sin6_addr);
}

int main(int argc, char *argv[]) {
    int sockfd, numbytes;
    char buf[MAXDATASIZE];
    struct addrinfo hints, *servinfo, *it;
    int err;
    char s[INET6_ADDRSTRLEN];

    if (argc != 3) {
        fprintf(stderr, "usage: client hostname port\n");
        exit(1);
    }

    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;

    if ((err = getaddrinfo(argv[1], argv[2], &hints, &servinfo)) != 0) {
        fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(err));
    }

    for (it = servinfo; it != NULL; it = it->ai_next) {
        if ((sockfd = socket(it->ai_family, it->ai_socktype, it->ai_protocol)) == -1) {
            perror("client: socket");
            continue;
        }

        if (connect(sockfd, it->ai_addr, it->ai_addrlen) == -1) {
            close(sockfd);
            perror("client: connect");
            continue;
        }

        break;
    }

    if (it == NULL) {
        fprintf(stderr, "client: failed to connect\n");
        return 2;
    }

    inet_ntop(it->ai_family, get_in_addr((struct sockaddr *) it->ai_addr), s, sizeof(s));
    printf("client: connecting to %s\n", s);

    freeaddrinfo(servinfo);

    if ((numbytes = recv(sockfd, buf, MAXDATASIZE-1, 0)) == -1) {
        perror("recv");
        exit(1);
    }

    buf[numbytes] = '\0';

    printf("client: received '%s'\n", buf);

    close(sockfd);

    return 0;
}
