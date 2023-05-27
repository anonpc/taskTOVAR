package org.example.context.commands;

import org.example.annotation.Injectable;

@Injectable
public abstract class Command {
    public abstract boolean execute();
}
