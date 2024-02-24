import fs from 'node:fs/promises';

const lineCount = parseInt(process.argv[2]);
const files = process.argv.slice(3);

let completeOutput = [];

for (const file of files) {
  let output = [];

  output.push(file);
  output.push('--------');

  const content = await fs.readFile(file, { encoding: 'utf8' });

  output.push(
    content.split('\n')
      .slice(0, lineCount)
      .join('\n')
  );

  completeOutput.push(output.join('\n'));
}

console.log(completeOutput.join('\n\n'));