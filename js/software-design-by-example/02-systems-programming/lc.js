import fs from 'fs';

const files = process.argv.slice(2);

const run = async (cb) => {
  const total = await cb();
  console.log(`total ${total}`);
};

run(async () => {
  let total = 0;

  for (const file of files) {
    const size = await new Promise((resolve) => {
      fs.stat(file, (_err, { size }) => {
        console.log(`${file} ${size}`);

        resolve(size);
      });
    });

    total += size;
  }

  return total;
});
