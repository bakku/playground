/*
** stream_socket_server.c -- small server which just sends Hello, world. you can test it with telnet or netcat
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <sys/wait.h>
#include <signal.h>

#define BACKLOG 10     // how many pending connections

void sigchld_handler(int s) {
    int saved_errno = errno;

    while(waitpid(-1, NULL, WNOHANG) > 0);

    errno = saved_errno;
}

void *get_in_addr(struct sockaddr *sa) {
    if (sa->sa_family == AF_INET) {
        return &(((struct sockaddr_in *) sa)->sin_addr);
    }

    return &(((struct sockaddr_in6 *) sa)->sin6_addr);
}

int main(int argc, char *argv[]) {
    int listen_socket_fd, new_conn_fd;
    struct addrinfo hints, *servinfo, *it;
    struct sockaddr_storage connector_addr_info;
    socklen_t sin_size;
    struct sigaction sa;
    int yes = 1;
    char s[INET6_ADDRSTRLEN];
    int err;

    if (argc == 1) {
        fprintf(stderr, "no port given\n");
        exit(1);
    }

    memset(&hints, 0, sizeof(hints));
    hints.ai_family   = AF_UNSPEC;     // IPv4 or IPv6, does not matter
    hints.ai_socktype = SOCK_STREAM;   // Socket streams (TCP)
    hints.ai_flags    = AI_PASSIVE;    // Use own IP

    if ((err = getaddrinfo(NULL, argv[1], &hints, &servinfo)) != 0) {
        fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(err));
        return 1;
    }

    // loop through all results and bind to the first we can
    for (it = servinfo; it != NULL; it = it->ai_next) {
        if ((listen_socket_fd = socket(it->ai_family, it->ai_socktype, it->ai_protocol)) == -1) {
            perror("server: socket");
            continue;
        }

        if (setsockopt(listen_socket_fd, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int)) == -1) {
            perror("setsockopt");
            continue;
        }

        if (bind(listen_socket_fd, it->ai_addr, it->ai_addrlen) == -1) {
            close(listen_socket_fd);
            perror("server: bind");
            continue;
        }

        break;
    }

    freeaddrinfo(servinfo);

    if (it == NULL) {
        fprintf(stderr, "server: failed to bind\n");
        exit(1);
    }

    if (listen(listen_socket_fd, BACKLOG) == -1) {
        perror("listen");
        exit(1);
    }

    sa.sa_handler = sigchld_handler;
    sigemptyset(&sa.sa_mask);
    sa.sa_flags = SA_RESTART;

    if (sigaction(SIGCHLD, &sa, NULL) == -1) {
        perror("sigaction");
        exit(1);
    }

    printf("server: waiting for connections...\n");

    while (1) {
        sin_size = sizeof(connector_addr_info);
        new_conn_fd = accept(listen_socket_fd, (struct sockaddr *) &connector_addr_info, &sin_size);

        if (new_conn_fd == -1) {
            perror("accept");
            continue;
        }

        inet_ntop(connector_addr_info.ss_family, get_in_addr((struct sockaddr *)&connector_addr_info), s, sizeof(s));

        printf("server: got connection from %s\n", s);

        if (!fork()) {
            close(listen_socket_fd);

            if (send(new_conn_fd, "Hello, world!", 13, 0) == -1) {
                perror("send");
            }

            close(new_conn_fd);
            exit(0);
        }

        close(new_conn_fd);
    }

    return 0;
}
